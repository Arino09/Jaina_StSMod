package jaina.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class ArchmageStuff extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("ArchmageStuff");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public ArchmageStuff() {
        super(ID, false, RELIC_STRINGS, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        IHelper.getTempCard(IHelper.generateRandomJainaCards(1, false, false, true, false, false).get(0));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArchmageStuff();
    }
}