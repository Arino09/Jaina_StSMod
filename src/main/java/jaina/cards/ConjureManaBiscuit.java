package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class ConjureManaBiscuit extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ConjureManaBiscuit");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ConjureManaBiscuit() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE);
        cardsToPreview = new ManaBiscuit();
    }

    @Override
    public void upp() {
        cardsToPreview.upgrade();
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        IHelper.getTempCard(new ManaBiscuit());
    }

    @Override
    public AbstractCard makeCopy() {
        return new ConjureManaBiscuit();
    }

}
