package jaina.modCore;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomUnlockBundle;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import jaina.cards.*;
import jaina.characters.JainaCharacter;
import jaina.relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class Core implements EditKeywordsSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, AddAudioSubscriber, SetUnlocksSubscriber {
    // 显示的颜色
    public static final Color COLOR = new Color(110.0F / 255.0F, 145.0F / 255.0F, 237.0F / 255.0F, 1.0F);
    // Mod的ID
    public static final String MOD_ID = "jaina";
    public static final boolean unlockEverything = false;
    // 人物选择界面按钮的图片
    private static final String CHAR_BUTTON = "jaina/img/char/Character_Button.png";
    // 人物选择界面的图像
    private static final String CHAR_PORTRAIT = "jaina/img/char/Character_Portrait.png";
    // 卡牌背景（小）
    private static final String BG_ATTACK_512 = "jaina/img/512/bg_attack_512.png";
    private static final String BG_SKILL_512 = "jaina/img/512/bg_skill_512.png";
    private static final String BG_POWER_512 = "jaina/img/512/bg_power_512.png";
    // 左上角的能量图标
    private static final String ENERGY_ORB = "jaina/img/char/cost_orb.png";
    // 卡牌背景（大）
    private static final String BG_ATTACK_1024 = "jaina/img/1024/bg_attack.png";
    private static final String BG_SKILL_1024 = "jaina/img/1024/bg_skill.png";
    private static final String BG_POWER_1024 = "jaina/img/1024/bg_power.png";
    // 大能量（用于大图展示）
    private static final String BIG_ORB = "jaina/img/char/card_orb.png";
    // 小能量（用于描述等）
    private static final String SMALL_ORB = "jaina/img/char/small_orb.png";

    public Core() {
        BaseMod.subscribe(this);
        BaseMod.addColor(JainaEnums.JAINA_COLOR, COLOR, COLOR, COLOR, COLOR, COLOR, COLOR, COLOR,
                BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENERGY_ORB, BG_ATTACK_1024,
                BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new Core();
    }

    public static void registerUnlockSuiteAlternating(String bundle1card1, String bundle1card2, String bundle1card3,
                                                      String bundle2relic1, String bundle2relic2, String bundle2relic3,
                                                      String bundle3card1, String bundle3card2, String bundle3card3,
                                                      String bundle4relic1, String bundle4relic2, String bundle4relic3,
                                                      String bundle5card1, String bundle5card2, String bundle5card3,
                                                      AbstractPlayer.PlayerClass player) {

        registerUnlockCardBundle(player, 0, bundle1card1, bundle1card2, bundle1card3);
        registerUnlockRelicBundle(player, 1, bundle2relic1, bundle2relic2, bundle2relic3);
        registerUnlockCardBundle(player, 2, bundle3card1, bundle3card2, bundle3card3);
        registerUnlockRelicBundle(player, 3, bundle4relic1, bundle4relic2, bundle4relic3);
        registerUnlockCardBundle(player, 4, bundle5card1, bundle5card2, bundle5card3);
    }

    private static void registerUnlockCardBundle(AbstractPlayer.PlayerClass player, int index,
                                                 String card1, String card2, String card3) {
        CustomUnlockBundle currentBundle = new CustomUnlockBundle(card1, card2, card3);
        UnlockTracker.addCard(card1);
        UnlockTracker.addCard(card2);
        UnlockTracker.addCard(card3);
        BaseMod.addUnlockBundle(currentBundle, player, index);
        if (unlockEverything || UnlockTracker.unlockProgress.getInteger(player.toString() + "UnlockLevel") > index + 1) {
            UnlockTracker.unlockCard(card1);
            UnlockTracker.unlockCard(card2);
            UnlockTracker.unlockCard(card3);
        }
    }

    private static void registerUnlockRelicBundle(AbstractPlayer.PlayerClass player, int index,
                                                  String relic1, String relic2, String relic3) {
        CustomUnlockBundle currentBundle = new CustomUnlockBundle(AbstractUnlock.UnlockType.RELIC, relic1, relic2, relic3);
        UnlockTracker.addRelic(relic1);
        UnlockTracker.addRelic(relic2);
        UnlockTracker.addRelic(relic3);
        BaseMod.addUnlockBundle(currentBundle, player, index);
        if (unlockEverything || UnlockTracker.unlockProgress.getInteger(player.toString() + "UnlockLevel") > index) {
            while (UnlockTracker.lockedRelics.contains(relic1))
                UnlockTracker.lockedRelics.remove(relic1);
            while (UnlockTracker.lockedRelics.contains(relic2))
                UnlockTracker.lockedRelics.remove(relic2);
            while (UnlockTracker.lockedRelics.contains(relic3))
                UnlockTracker.lockedRelics.remove(relic3);
            UnlockTracker.markRelicAsSeen(relic1);
            UnlockTracker.markRelicAsSeen(relic2);
            UnlockTracker.markRelicAsSeen(relic3);
        }
    }

    // 当baseMod开始注册角色时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        // 添加角色
        BaseMod.addCharacter(new JainaCharacter(CardCrawlGame.playerName),
                CHAR_BUTTON, CHAR_PORTRAIT, JainaEnums.JAINA_CLASS);
        addPotions();
    }

    // 当baseMod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // 自动添加卡牌
        new AutoAdd(MOD_ID)
                .packageFilter("jaina.cards")
                .setDefaultSeen(true)
                .cards();
        new AutoAdd(MOD_ID)
                .packageFilter("jaina.cards.optionCards")
                .setDefaultSeen(true)
                .cards();
    }

    // 当baseMod开始注册mod遗物时，便会调用这个函数
    @Override
    public void receiveEditRelics() {
        // 自动添加遗物到角色遗物池
        new AutoAdd(MOD_ID)
                .packageFilter("jaina.relics")
                .any(CustomRelic.class, (info, relic) -> {
                    BaseMod.addRelicToCustomPool(relic, JainaEnums.JAINA_COLOR);
                    if (info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    // 添加药水
    private void addPotions() {
        System.out.println("Adding Jaina potions: ");
        new AutoAdd(MOD_ID)
                .packageFilter("jaina.potions")
                .any(AbstractPotion.class, (info, potion) -> addPotionToJaina(potion));
        System.out.println("Jaina potions added.");
    }

    private void addPotionToJaina(AbstractPotion potion) {
        String id = potion.ID;
        BaseMod.addPotion(potion.getClass(), null, null, null, id, JainaEnums.JAINA_CLASS);
        System.out.println("Added potion: " + id.substring(6));
    }

    //加载本地化资源
    public void receiveEditStrings() {
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "jaina/localization/" + lang + "/character.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "jaina/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "jaina/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "jaina/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "jaina/localization/" + lang + "/potions.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "jaina/localization/" + lang + "/ui.json");
    }

    //注册新关键词
    @Override
    public void receiveEditKeywords() {
        //按语言读取json本地化文件
        Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }
        //json reader
        String json = Gdx.files.internal("jaina/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));

        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // add (mod_id, proper name, names[], description)
                BaseMod.addKeyword(MOD_ID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveAddAudio() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            BaseMod.addAudio(IHelper.makeID("pick"), "jaina/sound/pick_zhs.ogg");
        } else {
            BaseMod.addAudio(IHelper.makeID("pick"), "jaina/sound/pick_eng.ogg");
        }
        // Beta测试阶段前不使用
//        BaseMod.addAudio(IHelper.makeID("ArcaneFizzle"), "jaina/sound/Arcane_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneImpact"), "jaina/sound/Arcane_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcanePreCast"), "jaina/sound/Arcane_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneStart"), "jaina/sound/Arcane_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneExplosion"), "jaina/sound/ArcaneExplosion.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneIntellect"), "jaina/sound/ArcaneIntellect.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneMissiles"), "jaina/sound/ArcaneMissiles.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcanePortal"), "jaina/sound/ArcanePortal.ogg");
//        BaseMod.addAudio(IHelper.makeID("ConeOfCold"), "jaina/sound/ConeOfCold.ogg");
//        BaseMod.addAudio(IHelper.makeID("CounterSpell"), "jaina/sound/CounterSpell.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_Cast"), "jaina/sound/FelFire_Cast.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_Fizzle"), "jaina/sound/FelFire_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_PreCast"), "jaina/sound/FelFire_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_Start"), "jaina/sound/FelFire_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Cast"), "jaina/sound/Fire_Cast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Cast_Large"), "jaina/sound/Fire_Cast_Large.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_End"), "jaina/sound/Fire_End.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Fizzle"), "jaina/sound/Fire_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Impact"), "jaina/sound/Fire_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Impact_Large"), "jaina/sound/Fire_Impact_Large.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Impact_Small"), "jaina/sound/Fire_Impact_Small.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_PreCast"), "jaina/sound/Fire_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_PreCast_Large"), "jaina/sound/Fire_PreCast_Large.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Start"), "jaina/sound/Fire_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("FireAE_Impact"), "jaina/sound/FireAE_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_BirthLoop"), "jaina/sound/Fireball_BirthLoop.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_BirthStart"), "jaina/sound/Fireball_BirthStart.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_SpellCancel"), "jaina/sound/Fireball_SpellCancel.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_SpellCast"), "jaina/sound/Fireball_SpellCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_SpellImpact"), "jaina/sound/Fireball_SpellImpact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireblast_Impact"), "jaina/sound/Fireblast_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_BirthLoop"), "jaina/sound/Freeze_BirthLoop.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_BirthStart"), "jaina/sound/Freeze_BirthStart.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_HoverState"), "jaina/sound/Freeze_HoverState.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_SpellCast"), "jaina/sound/Freeze_SpellCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_SpellImpact"), "jaina/sound/Freeze_SpellImpact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_StateEnd"), "jaina/sound/Freeze_StateEnd.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Cast"), "jaina/sound/Frost_Cast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Cast_Small"), "jaina/sound/Frost_Cast_Small.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_End"), "jaina/sound/Frost_End.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Fizzle"), "jaina/sound/Frost_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Impact"), "jaina/sound/Frost_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Impact_Small"), "jaina/sound/Frost_Impact_Small.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_PreCast"), "jaina/sound/Frost_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Start"), "jaina/sound/Frost_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("FrostArmorTarget"), "jaina/sound/FrostArmorTarget.ogg");
//        BaseMod.addAudio(IHelper.makeID("FrostBoltHit"), "jaina/sound/FrostBoltHit.ogg");
//        BaseMod.addAudio(IHelper.makeID("FrostNova"), "jaina/sound/FrostNova.ogg");
//        BaseMod.addAudio(IHelper.makeID("IceBarrier_Impact"), "jaina/sound/IceBarrier_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("IceBlock_Impact"), "jaina/sound/IceBlock_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Poison_Impact1"), "jaina/sound/Poison_Impact1.ogg");
//        BaseMod.addAudio(IHelper.makeID("Poison_Impact2"), "jaina/sound/Poison_Impact2.ogg");
//        BaseMod.addAudio(IHelper.makeID("Poison_Impact3"), "jaina/sound/Poison_Impact3.ogg");
//        BaseMod.addAudio(IHelper.makeID("Polymorph"), "jaina/sound/Polymorph.ogg");
//        BaseMod.addAudio(IHelper.makeID("Secret_Birth"), "jaina/sound/Secret_Birth.ogg");
//        BaseMod.addAudio(IHelper.makeID("Secret_Trigger"), "jaina/sound/Secret_Trigger.ogg");
//        BaseMod.addAudio(IHelper.makeID("UnstablePortal"), "jaina/sound/UnstablePortal.ogg");
    }

    @Override
    public void receiveSetUnlocks() {
        registerUnlockSuiteAlternating(IceLance.ID, BreathOfSindragosa.ID, IceShard.ID,
                ArcaniteCrystal.ID, RubySpellstone.ID, RobesOfGaudiness.ID,
                TomeOfIntellect.ID, CabalistsTome.ID, ShiftingScroll.ID,
                AscendantScroll.ID, Aluneth.ID, BookOfWonders.ID,
                FontOfPower.ID, Evocation.ID, Wish.ID, JainaEnums.JAINA_CLASS);
    }
}
