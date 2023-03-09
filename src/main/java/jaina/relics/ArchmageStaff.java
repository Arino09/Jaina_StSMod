package jaina.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

import java.util.ArrayList;

public class ArchmageStaff extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("ArchmageStaff");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public ArchmageStaff() {
        super(ID, false, RELIC_STRINGS, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        ArrayList<AbstractCard> cardRng = IHelper.generateRandomJainaCards(false, false, true, false);
        IHelper.getTempCard(IHelper.getFewCards(cardRng, 1, false).get(0));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArchmageStaff();
    }
}