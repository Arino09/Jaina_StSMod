package Jaina.characters;

import Jaina.ModCore.Core;
import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import Jaina.cards.Defend;
import Jaina.cards.Fireball;
import Jaina.cards.Frostbolt;
import Jaina.cards.Strike;
import Jaina.relics.ArchmageStuff;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.PureWater;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class JainaCharacter extends CustomPlayer {
    // 战斗时角色立绘
    private static final String STAND = "Jaina/img/char/character.png";
    // 火堆的角色立绘（行动前）
    private static final String SHOULDER_1 = "Jaina/img/char/shoulder.png";
    // 火堆的角色立绘（行动后）
    private static final String SHOULDER_2 = "Jaina/img/char/shoulder2.png";
    // 角色死亡图像
    private static final String CORPSE_IMAGE = "Jaina/img/char/corpse.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "Jaina/img/UI/orb/layer5.png",
            "Jaina/img/UI/orb/layer4.png",
            "Jaina/img/UI/orb/layer3.png",
            "Jaina/img/UI/orb/layer2.png",
            "Jaina/img/UI/orb/layer1.png",
            "Jaina/img/UI/orb/layer6.png",
            "Jaina/img/UI/orb/layer5d.png",
            "Jaina/img/UI/orb/layer4d.png",
            "Jaina/img/UI/orb/layer3d.png",
            "Jaina/img/UI/orb/layer2d.png",
            "Jaina/img/UI/orb/layer1d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    // 角色的本地化文本，如卡牌的本地化文本一样，如何书写见下
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("jaina:Jaina");

    public JainaCharacter(String name) {
        super(name, JainaEnums.JAINA_CLASS, ORB_TEXTURES,  "Jaina/img/UI/orb/vfx.png", LAYER_SPEED, null, null);

        // 角色对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);

        // 初始化你的角色，如果你的角色只有一张图，那么第一个参数填写你角色图片的路径。
        this.initializeClass(
                STAND, // 角色图片
                SHOULDER_2, SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 角色碰撞箱大小，越大的角色模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );

        // 如果你的人物没有动画，那么这些不需要写
        // this.loadAnimation("ExampleModResources/img/char/character.atlas", "ExampleModResources/img/char/character.json", 1.8F);
        // AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        // e.setTime(e.getEndTime() * MathUtils.random());
        // e.setTimeScale(1.2F);

    }

    /**
     * 初始卡组的ID，可直接写或引用变量
     * @return 初始卡组列表
     */
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> deck = new ArrayList<>();
        for(int x = 0; x < 5; x++) {
            deck.add(Strike.ID);
            deck.add(Defend.ID);
        }
        deck.add(Fireball.ID);
        deck.add(Frostbolt.ID);
        return deck;
    }

    /**
     * 设定初始遗物
     * @return 初始遗物列表
     */
    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();
        relics.add(ArchmageStuff.ID);
        return relics;
    }

    /**
     * 角色初始信息
     * @return 角色选择信息
     */
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], // 角色名字
                characterStrings.TEXT[0], // 角色介绍
                60, // 当前血量
                60, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this, // 别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // 别动
        );
    }
    /**
     * 角色名
     * @return 角色名字（出现在游戏左上角）
     */
    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    /**
     * 卡牌颜色
     * @return 卡牌颜色
     */
    @Override
    public AbstractCard.CardColor getCardColor() {
        return JainaEnums.JAINA_COLOR;
    }

    /**
     * 卡牌选择界面选择该牌的颜色
     * @return 颜色
     */
    @Override
    public Color getCardRenderColor() {
        return Core.JAINA_COLOR;
    }
    /**
     * 翻牌事件出现的你的职业牌（一般设为打击）
     */
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    /**
     * 卡牌轨迹颜色
     * @return 卡牌轨迹颜色
     */
    @Override
    public Color getCardTrailColor() {
        return Core.JAINA_COLOR;
    }

    /**
     * 高进阶带来的生命值损失
     * @return 损失值
     */
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    /**
     * 卡牌的能量字体，没必要修改
     * @return 字体
     */
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    /**
     * 角色选择界面点击你的角色按钮时触发的方法，这里为屏幕轻微震动
     */
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.play(IHelper.makeID("select"));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    /**
     * 碎心胜利图片
     * @return 图片数组
     */
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("Jades/img/char/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("Jades/img/char/Victory2.png"));
        panels.add(new CutscenePanel("Jades/img/char/Victory3.png"));
        return panels;
    }

    /**
     * 自定义模式选择你的角色时播放的音效
     * @return 选择角色音效
     */
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    /**
     * 本地化角色名称，显示在游戏左上角
     * @return 角色名称
     */
    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    /**
     * 创建角色实例，不需要修改
     * @return 角色实例
     */
    @Override
    public AbstractPlayer newInstance() {
        return new JainaCharacter(this.name);
    }

    /**
     * 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”之类的）
     * @return 本地化台词
     */
    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    /**
     * 打心脏的颜色，不是很明显
     * @return 颜色
     */
    @Override
    public Color getSlashAttackColor() {
        return Core.JAINA_COLOR;
    }

    /**
     * 打心脏造成伤害时的特效
     * @return 特效Action
     */
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    /**
     * 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
     * @return 0为“他”，1为“她”
     */
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }
}
