package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.unique.IncantersPower;


public class IncantersFlow extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("IncantersFlow");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public IncantersFlow() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new IncantersPower(p));
    }

    @Override
    public AbstractCard makeCopy() {
        return new IncantersFlow();
    }

}
