package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class IncantersEndAction extends AbstractGameAction {

    @Override
    public void update() {
        restoreCost(AbstractDungeon.player.hand.group);
        restoreCost(AbstractDungeon.player.discardPile.group);
        restoreCost(AbstractDungeon.player.drawPile.group);
        restoreCost(AbstractDungeon.player.exhaustPile.group);
        this.isDone = true;
    }

    private static void restoreCost(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.cardID.startsWith("jaina:") && !c.isStarterStrike() && !c.isStarterDefend()) {
                c.setCostForTurn(c.cost);
                c.freeToPlayOnce = false;
                c.isCostModifiedForTurn = false;
            }
        }
    }
}
