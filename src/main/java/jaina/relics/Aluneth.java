package jaina.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class Aluneth extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("Aluneth");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public Aluneth() {
        super(ID, false, RELIC_STRINGS, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStartPostDraw() {
        addToBot(new DrawCardAction(3));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Aluneth();
    }
}