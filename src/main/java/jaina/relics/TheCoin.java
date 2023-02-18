package jaina.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class TheCoin extends AbstractJainaRelic implements ClickableRelic {
    public static final String ID = IHelper.makeID("TheCoin");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public TheCoin() {
        super(ID, false, RELIC_STRINGS, AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.usedUp = false;
    }

    @Override
    public void onRightClick() {
        if (!usedUp) {
            if (AbstractDungeon.player.hand.size() != 10) {
                IHelper.getTempCard(new jaina.cards.TheCoin());
            }
            usedUp = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TheCoin();
    }

}
