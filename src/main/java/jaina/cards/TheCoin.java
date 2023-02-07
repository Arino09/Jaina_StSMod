package jaina.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;


public class TheCoin extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("TheCoin");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public TheCoin() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, CardColor.COLORLESS,
                CardRarity.SPECIAL, CardTarget.NONE, CardTags.EMPTY);
        exhaust = true;
        selfRetain = true;
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheCoin();
    }

}
