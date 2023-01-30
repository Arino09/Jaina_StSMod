package Jaina.ModCore;

import Jaina.characters.JainaCharacter;
import Jaina.relics.ArchmageStuff;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;


@SpireInitializer
public class Core implements EditKeywordsSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, AddAudioSubscriber {
    // 人物选择界面按钮的图片
    private static final String CHAR_BUTTON = "Jaina/img/char/Character_Button.png";
    // 人物选择界面的图像
    private static final String CHAR_PORTRAIT = "Jaina/img/char/Character_Portrait.png";
    // 卡牌背景（小）
    private static final String BG_ATTACK_512 = "Jaina/img/512/bg_attack_512.png";
    private static final String BG_SKILL_512 = "Jaina/img/512/bg_skill_512.png";
    private static final String BG_POWER_512 = "Jaina/img/512/bg_power_512.png";
    // 左上角的能量图标
    private static final String ENERGY_ORB = "Jaina/img/char/cost_orb.png";
    // 卡牌背景（大）
    private static final String BG_ATTACK_1024 = "Jaina/img/1024/bg_attack.png";
    private static final String BG_SKILL_1024 = "Jaina/img/1024/bg_skill.png";
    private static final String BG_POWER_1024 = "Jaina/img/1024/bg_power.png";
    // 大能量（用于大图展示）
    private static final String BIG_ORB = "Jaina/img/char/card_orb.png";
    // 小能量（用于描述等）
    private static final String SMALL_ORB = "Jaina/img/char/small_orb.png";
    // 显示的颜色
    public static final Color JAINA_COLOR = new Color(110.0F / 255.0F, 145.0F / 255.0F, 237.0F / 255.0F, 1.0F);
    // Mod的ID
    public static final String MOD_ID = "jaina";

    public Core() {
        BaseMod.subscribe(this);
        BaseMod.addColor(JainaEnums.JAINA_COLOR, JAINA_COLOR, JAINA_COLOR, JAINA_COLOR, JAINA_COLOR, JAINA_COLOR, JAINA_COLOR, JAINA_COLOR,
                BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENERGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new Core();
    }

    // 当baseMod开始注册角色时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        // 添加角色
        BaseMod.addCharacter(new JainaCharacter(CardCrawlGame.playerName), CHAR_BUTTON, CHAR_PORTRAIT, JainaEnums.JAINA_CLASS);
    }

    // 当baseMod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // 自动添加卡牌
        new AutoAdd(MOD_ID)
                .packageFilter("Jaina.cards")
                .setDefaultSeen(true)
                .cards();
        new AutoAdd(MOD_ID)
                .packageFilter("Jaina.cards.optionCards")
                .setDefaultSeen(true)
                .cards();
    }

    // 当baseMod开始注册mod遗物时，便会调用这个函数
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new ArchmageStuff(), JainaEnums.JAINA_COLOR);
    }

    //加载本地化资源
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "Jaina/localization/" + lang + "/character.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "Jaina/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Jaina/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Jaina/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "Jaina/localization/" + lang + "/ui.json");
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
        String json = Gdx.files.internal("Jaina/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));

        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(MOD_ID, keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(IHelper.makeID("select"), "Jaina/sound/Select_voice.ogg");
        // Beta测试阶段前不使用
//        BaseMod.addAudio(IHelper.makeID("ArcaneFizzle"), "Jaina/sound/Arcane_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneImpact"), "Jaina/sound/Arcane_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcanePreCast"), "Jaina/sound/Arcane_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneStart"), "Jaina/sound/Arcane_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneExplosion"), "Jaina/sound/ArcaneExplosion.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneIntellect"), "Jaina/sound/ArcaneIntellect.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcaneMissiles"), "Jaina/sound/ArcaneMissiles.ogg");
//        BaseMod.addAudio(IHelper.makeID("ArcanePortal"), "Jaina/sound/ArcanePortal.ogg");
//        BaseMod.addAudio(IHelper.makeID("ConeOfCold"), "Jaina/sound/ConeOfCold.ogg");
//        BaseMod.addAudio(IHelper.makeID("CounterSpell"), "Jaina/sound/CounterSpell.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_Cast"), "Jaina/sound/FelFire_Cast.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_Fizzle"), "Jaina/sound/FelFire_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_PreCast"), "Jaina/sound/FelFire_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("FelFire_Start"), "Jaina/sound/FelFire_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Cast"), "Jaina/sound/Fire_Cast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Cast_Large"), "Jaina/sound/Fire_Cast_Large.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_End"), "Jaina/sound/Fire_End.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Fizzle"), "Jaina/sound/Fire_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Impact"), "Jaina/sound/Fire_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Impact_Large"), "Jaina/sound/Fire_Impact_Large.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Impact_Small"), "Jaina/sound/Fire_Impact_Small.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_PreCast"), "Jaina/sound/Fire_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_PreCast_Large"), "Jaina/sound/Fire_PreCast_Large.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fire_Start"), "Jaina/sound/Fire_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("FireAE_Impact"), "Jaina/sound/FireAE_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_BirthLoop"), "Jaina/sound/Fireball_BirthLoop.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_BirthStart"), "Jaina/sound/Fireball_BirthStart.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_SpellCancel"), "Jaina/sound/Fireball_SpellCancel.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_SpellCast"), "Jaina/sound/Fireball_SpellCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireball_SpellImpact"), "Jaina/sound/Fireball_SpellImpact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Fireblast_Impact"), "Jaina/sound/Fireblast_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_BirthLoop"), "Jaina/sound/Freeze_BirthLoop.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_BirthStart"), "Jaina/sound/Freeze_BirthStart.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_HoverState"), "Jaina/sound/Freeze_HoverState.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_SpellCast"), "Jaina/sound/Freeze_SpellCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_SpellImpact"), "Jaina/sound/Freeze_SpellImpact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Freeze_StateEnd"), "Jaina/sound/Freeze_StateEnd.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Cast"), "Jaina/sound/Frost_Cast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Cast_Small"), "Jaina/sound/Frost_Cast_Small.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_End"), "Jaina/sound/Frost_End.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Fizzle"), "Jaina/sound/Frost_Fizzle.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Impact"), "Jaina/sound/Frost_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Impact_Small"), "Jaina/sound/Frost_Impact_Small.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_PreCast"), "Jaina/sound/Frost_PreCast.ogg");
//        BaseMod.addAudio(IHelper.makeID("Frost_Start"), "Jaina/sound/Frost_Start.ogg");
//        BaseMod.addAudio(IHelper.makeID("FrostArmorTarget"), "Jaina/sound/FrostArmorTarget.ogg");
//        BaseMod.addAudio(IHelper.makeID("FrostBoltHit"), "Jaina/sound/FrostBoltHit.ogg");
//        BaseMod.addAudio(IHelper.makeID("FrostNova"), "Jaina/sound/FrostNova.ogg");
//        BaseMod.addAudio(IHelper.makeID("IceBarrier_Impact"), "Jaina/sound/IceBarrier_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("IceBlock_Impact"), "Jaina/sound/IceBlock_Impact.ogg");
//        BaseMod.addAudio(IHelper.makeID("Poison_Impact1"), "Jaina/sound/Poison_Impact1.ogg");
//        BaseMod.addAudio(IHelper.makeID("Poison_Impact2"), "Jaina/sound/Poison_Impact2.ogg");
//        BaseMod.addAudio(IHelper.makeID("Poison_Impact3"), "Jaina/sound/Poison_Impact3.ogg");
//        BaseMod.addAudio(IHelper.makeID("Polymorph"), "Jaina/sound/Polymorph.ogg");
//        BaseMod.addAudio(IHelper.makeID("Secret_Birth"), "Jaina/sound/Secret_Birth.ogg");
//        BaseMod.addAudio(IHelper.makeID("Secret_Trigger"), "Jaina/sound/Secret_Trigger.ogg");
//        BaseMod.addAudio(IHelper.makeID("UnstablePortal"), "Jaina/sound/UnstablePortal.ogg");

    }

    public static boolean unlockEverything = false;

}
