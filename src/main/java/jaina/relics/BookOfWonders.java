package jaina.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.cards.ScrollOfWonder;
import jaina.modCore.IHelper;

public class BookOfWonders extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("BookOfWonders");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public BookOfWonders() {
        super(ID, false, RELIC_STRINGS, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        for (int i = 0; i < 5; i++) {
            AbstractDungeon.player.drawPile.addToRandomSpot(new ScrollOfWonder());
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BookOfWonders();
    }
}