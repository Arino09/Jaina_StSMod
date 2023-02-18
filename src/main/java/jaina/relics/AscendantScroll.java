package jaina.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class AscendantScroll extends AbstractJainaRelic implements ClickableRelic {
    public static final String ID = IHelper.makeID("AscendantScroll");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public AscendantScroll() {
        super(ID, false, RELIC_STRINGS, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.player.energy.energy >= 2) {
            AbstractDungeon.player.energy.use(2);
        }
        if (AbstractDungeon.player.hand.size() <= 10) {
            AbstractCard card = IHelper.generateRandomJainaCards(1, true, true, true, false, false).get(0);
            if (card.costForTurn <= 2 && card.costForTurn >= 0) {
                card.setCostForTurn(0);
                card.freeToPlayOnce = true;
            } else {
                card.setCostForTurn(card.costForTurn - 2);
            }
            IHelper.getTempCard(card);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AscendantScroll();
    }


}