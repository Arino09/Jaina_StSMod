package jaina.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.cards.ScrollOfWonder;
import jaina.modCore.IHelper;

public class BookOfWonders extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("BookOfWonders");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final int AMT = 5;

    public BookOfWonders() {
        super(ID, false, RELIC_STRINGS, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    protected void initializeTips() {
        super.initializeTips();
        this.tips.add(new CardPowerTip(new ScrollOfWonder()));
    }

    @Override
    public void atBattleStart() {
        for (int i = 0; i < AMT; i++) {
            AbstractDungeon.player.drawPile.addToRandomSpot(new ScrollOfWonder());
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BookOfWonders();
    }
}