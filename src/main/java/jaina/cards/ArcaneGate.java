package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.unique.ArcaneGatePower;


public class ArcaneGate extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ArcaneGate");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public ArcaneGate() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        setMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new ArcaneGatePower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneGate();
    }

}
