package jaina.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jaina.modCore.JainaEnums;
import jaina.powers.FrozenPower;
import jaina.powers.SpellForcePower;

public abstract class AbstractJainaCard extends CustomCard {

    /**
     * 构造函数
     *
     * @param ID          完整的卡牌ID
     * @param useTestArt  是否使用测试图片
     * @param cardStrings 卡牌本地化字段
     * @param cost        能量花费（-1为X，-2不显示消耗）
     * @param type        卡牌类型
     * @param color       卡牌颜色
     * @param rarity      卡牌稀有度
     * @param target      卡牌目标
     * @param tags        卡牌标签
     */
    public AbstractJainaCard(String ID, boolean useTestArt, CardStrings cardStrings, int cost,
                             CardType type, CardColor color, CardRarity rarity, CardTarget target, CardTags tags) {
        super(ID, cardStrings.NAME, useTestArt ? getTestImgPath(type) : getImgPath(type, ID), cost,
                cardStrings.DESCRIPTION, type, color, rarity, target);
        this.tags.add(tags);
    }

    /**
     * @param ID     完整的卡牌ID
     * @param imgUrl 卡牌图片
     * @param cost   能量花费（-1为X，-2不显示消耗）
     * @param type   卡牌类型
     * @param color  卡牌颜色
     * @param rarity 卡牌稀有度
     * @param target 卡牌目标
     */
    public AbstractJainaCard(String ID, CardStrings cardStrings, String imgUrl, int cost,
                             CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(ID, cardStrings.NAME, imgUrl, cost, cardStrings.DESCRIPTION, type, color, rarity, target);
    }

    /**
     * 获取指定卡牌的图片路径
     *
     * @param type 卡牌类型
     * @param id   完整卡牌id
     * @return 图片路径
     */
    public static String getImgPath(CardType type, String id) {
        String t;
        switch (type) {
            case ATTACK:
                t = "attack";
                break;
            case SKILL:
                t = "skill";
                break;
            case POWER:
                t = "power";
                break;
            case STATUS:
                t = "status";
                break;
            case CURSE:
                t = "curse";
                break;
            default:
                throw new IllegalArgumentException("Unexpect value: " + type);
        }
        return String.format("jaina/img/cards/%s/%s.png", t, id.substring(6));
    }

    /**
     * 获取卡牌测试图片路径
     *
     * @param type 卡牌类型
     * @return 测试图片路径
     */
    public static String getTestImgPath(CardType type) {
        String t;
        switch (type) {
            case ATTACK:
                t = "attack";
                break;
            case POWER:
                t = "power";
                break;
            case CURSE:
            case STATUS:
            case SKILL:
                t = "skill";
                break;
            default:
                throw new IllegalArgumentException("Unexpect value: " + type);
        }
        return String.format("jaina/img/cards/%s/test.png", t);
    }

    /**
     * 设置基础伤害值
     *
     * @param damage 伤害值
     */
    public void setDamage(int damage) {
        this.baseDamage = damage;
        this.damage = damage;
    }

    /**
     * 设置基础格挡值
     *
     * @param block 格挡值
     */
    public void setBlock(int block) {
        this.baseBlock = block;
        this.block = block;
    }

    /**
     * 设置基础特殊值
     *
     * @param number 特殊值
     */
    public void setMagicNumber(int number) {
        this.baseMagicNumber = number;
        this.magicNumber = number;
    }

    /**
     * 升级卡牌描述
     *
     * @param cardStrings 卡牌信息
     */
    public void upgradeDescription(CardStrings cardStrings) {

        if (cardStrings.UPGRADE_DESCRIPTION != null) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        }
        initializeDescription();
    }

    /**
     * 造成指定量伤害
     *
     * @param m    目标
     * @param base 伤害数值
     * @param ae   伤害效果
     */
    public void dealDamage(AbstractMonster m, int base, AbstractGameAction.AttackEffect ae) {

        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, base, this.damageTypeForTurn), ae));
    }

    /**
     * 随机造成伤害
     *
     * @param ae 伤害效果
     */
    public void dealRandDamage(AbstractGameAction.AttackEffect ae) {

        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), ae));
    }

    /**
     * 造成伤害
     *
     * @param m  目标
     * @param ae 伤害效果
     */
    public void dealDamage(AbstractMonster m, AbstractGameAction.AttackEffect ae) {

        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), ae));
    }

    /**
     * 造成AOE伤害
     *
     * @param ae 伤害效果
     */
    public void dealAoeDamage(AbstractGameAction.AttackEffect ae) {
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player,
                this.damage, this.damageTypeForTurn, ae));
    }

    /**
     * 获得格挡
     */
    public void gainBlock() {
        this.addToBot(new GainBlockAction(
                AbstractDungeon.player, this.block));
    }

    /**
     * 获得一定量格挡
     *
     * @param block 格挡值
     */
    public void gainBlock(int block) {
        this.addToBot(new GainBlockAction(
                AbstractDungeon.player, block));
    }

    /**
     * 抽牌
     *
     * @param n 抽牌数量
     */
    public void drawCards(int n) {
        this.addToBot(new DrawCardAction(n));
    }

    /**
     * 给予玩家一层能力
     *
     * @param power 能力
     */
    public void gainPower(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player, power));
    }

    /**
     * 给予目标生物一个能力
     *
     * @param power  能力
     * @param amount 能力层数
     */
    public void givePower(AbstractPower power, int amount) {
        this.addToBot(new ApplyPowerAction(power.owner,
                AbstractDungeon.player, power, amount));
    }

    /**
     * 冰冻所有敌人
     *
     * @param amount 冻结层数
     * @return 被冻结的敌人数量
     */
    public int frozenAllEnemy(int amount) {
        int frozenAmt = 0;
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (!m.isDead && !m.isDying) {
                this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new FrozenPower(m, amount)));
                if (m.intent == JainaEnums.FROZEN) frozenAmt++;
            }
        }
        return frozenAmt;
    }

    /**
     * 根据法术伤害在卡牌描述中更新魔法数
     *
     * @param strings 本地化文本
     */
    public void updateDescription(CardStrings strings) {
        if (AbstractDungeon.player.hasPower(SpellForcePower.POWER_ID)) {
            AbstractPower power = AbstractDungeon.player.getPower(SpellForcePower.POWER_ID);
            // 仅当法伤数值变化时才更新描述
            if (power.amount + baseMagicNumber > magicNumber) {
                this.magicNumber = power.amount + baseMagicNumber;
                this.rawDescription = strings.DESCRIPTION;
                if (magicNumber > 1) {
                    this.rawDescription += String.format(strings.EXTENDED_DESCRIPTION[0], magicNumber);
                } else {
                    this.rawDescription += String.format(strings.EXTENDED_DESCRIPTION[1], magicNumber);
                }
                initializeDescription();
            }
        } else {
            resetDescription(strings);
        }
    }

    /**
     * 重置卡牌描述为最初版
     *
     * @param strings 本地化文本
     */
    public void resetDescription(CardStrings strings) {
        rawDescription = strings.DESCRIPTION;
        if (upgraded) {
            upgradeDescription(strings);
        }
        initializeDescription();
    }

    //重写了升级方法，升级效果写在upp中即可
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upp();
            initializeDescription();
        }
    }

    //升级只需重写此方法
    public void upp() {
    }

}
