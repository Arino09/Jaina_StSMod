package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.DuplicateAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class Duplicate extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("Duplicate");
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Duplicate() {
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
        addToBot(new DuplicateAction(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Duplicate();
    }

}
