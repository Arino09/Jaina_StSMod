package jaina.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class SphereOfSapience extends AbstractJainaRelic implements ClickableRelic {
    public static final String ID = IHelper.makeID("SphereOfSapience");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final int AMT = 5;

    public SphereOfSapience() {
        super(ID, false, RELIC_STRINGS, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        usedUp = false;
    }

    @Override
    public void onRightClick() {
        if (!usedUp) {
            addToBot(new ScryAction(AMT));
            if (AbstractDungeon.player.hand.size() != 10) {
                addToBot(new DrawCardAction(1));
            }
            usedUp = true;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SphereOfSapience();
    }
}