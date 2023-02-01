package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class IncantersStartAction extends AbstractGameAction {

    private static void setFree(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.cardID.startsWith("jaina:") && !c.isStarterStrike() && !c.isStarterDefend()) {
                c.setCostForTurn(0);
                c.freeToPlayOnce = true;
            }
        }
    }

    @Override
    public void update() {
        setFree(AbstractDungeon.player.hand.group);
        setFree(AbstractDungeon.player.discardPile.group);
        setFree(AbstractDungeon.player.drawPile.group);
        setFree(AbstractDungeon.player.exhaustPile.group);
        this.isDone = true;
    }
}
