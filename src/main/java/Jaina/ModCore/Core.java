package Jaina.ModCore;

import Jaina.characters.JainaCharacter;
import Jaina.powers.FrozenPower;
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
public class Core implements EditKeywordsSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {
    // 人物选择界面按钮的图片
    private static final String CHAR_BUTTON = "Jaina/img/char/Character_Button.png";
    // 人物选择界面的立绘

    private static final String CHAR_PORTRAIT = "Jaina/img/char/Character_Portrait.png";
    private static final String BG_ATTACK_512 = "Jaina/img/512/bg_attack_512.png";
    private static final String BG_SKILL_512 = "Jaina/img/512/bg_skill_512.png";
    private static final String BG_POWER_512 = "Jaina/img/512/bg_power_512.png";
    private static final String ENERGY_ORB = "Jaina/img/char/cost_orb.png";
    private static final String BG_ATTACK_1024 = "Jaina/img/1024/bg_attack.png";
    private static final String BG_SKILL_1024 = "Jaina/img/1024/bg_skill.png";
    private static final String BG_POWER_1024 = "Jaina/img/1024/bg_power.png";
    private static final String BIG_ORB = "Jaina/img/char/card_orb.png";
    private static final String SMALL_ORB = "Jaina/img/char/small_orb.png";

    public static final Color JAINA_COLOR = new Color(52.0F / 255.0F,184.0F / 255.0F,173.0F / 255.0F, 1.0F);

    public Core(){
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
        new AutoAdd("jaina")
                .packageFilter("Jaina.cards")
                .setDefaultSeen(true)
                .cards();
    }
    // 当baseMod开始注册mod遗物时，便会调用这个函数
    @Override
    public void receiveEditRelics() {
        //BaseMod.addRelicToCustomPool(new JadeCharm(), JainaEnums.JAINA_COLOR);
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
                BaseMod.addKeyword("jaina", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

}
