package jaina.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.SpringWaterAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class SpringWater extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("SpringWater");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public SpringWater() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE);
        setMagicNumber(5);
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber, new SpringWaterAction()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpringWater();
    }


}
