package jaina.relics;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class Aluneth extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("Aluneth");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final int DRAW = 3;
    private static final int DAMAGE = 5;

    public Aluneth() {
        super(ID, false, RELIC_STRINGS, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStartPostDraw() {
        addToBot(new DrawCardAction(DRAW));
    }

    @Override
    public void onShuffle() {
        addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, DAMAGE)));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Aluneth();
    }
}