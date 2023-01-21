package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;

public class ArcaneIntellect extends AbstractJainaCard {
    
    public static final String ID = IHelper.makeID("ArcaneIntellect");
    private static final CardString CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    
    private static final int COST = 1;

    public ArcaneIntellect() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.setMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(magicNumber);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void makeCopy() {
        return new ArcaneIntellect();
    }

}
