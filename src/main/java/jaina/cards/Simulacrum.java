package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.ExhaustCardFromDrawAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class Simulacrum extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("Simulacrum");
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Simulacrum() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE);
        setMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustCardFromDrawAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Simulacrum();
    }

}
