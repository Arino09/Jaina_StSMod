package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class CramSession extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("CramSession");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public CramSession() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE);
        setMagicNumber(1);
    }

    @Override
    public void applyPowers() {
        updateDescription(CARD_STRINGS);
    }

    @Override
    public void onMoveToDiscard() {
        resetDescription(CARD_STRINGS);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CramSession();
    }

}
