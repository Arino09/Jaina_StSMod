package jaina.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class TheCoin extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("TheCoin");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public TheCoin() {
        super(ID, false, RELIC_STRINGS, AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.CLINK);
    }

    @Override
    protected void initializeTips() {
        super.initializeTips();
        tips.add(new CardPowerTip(new jaina.cards.TheCoin()));
    }

    @Override
    public void atBattleStart() {
        IHelper.getTempCard(new jaina.cards.TheCoin());
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TheCoin();
    }

}
