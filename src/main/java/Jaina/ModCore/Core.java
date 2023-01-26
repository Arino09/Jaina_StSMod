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
        BaseMod.addAudio(IHelper.makeID("select"), "Jaina/sound/select_voice.ogg");
    }

    public static boolean unlockEverything = false;

}