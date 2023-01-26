package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Strike");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    //卡牌基本信息
    private static final int COST = 1;

    public Strike() {
        //继承构造函数，传参为super(ID, 是否使用测试图片, 卡牌文本, 耗能, 类型, 颜色, 稀有度, 目标)
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);  //新标签
        this.tags.add(CardTags.STARTER_STRIKE);
        setDamage(6);
    }

    /**
     * 当卡牌被使用时，调用这个方法。
     *
     * @param p 你的玩家实体类。
     * @param m 指向的怪物类。（无指向时为null，包括攻击所有敌人时）
     */
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    //卡牌升级后的效果
    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike();
    }
}
