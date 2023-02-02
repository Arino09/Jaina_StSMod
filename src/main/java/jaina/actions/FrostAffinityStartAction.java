package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class FrostAffinityStartAction extends AbstractGameAction {

    private static void setFree(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.hasTag(JainaEnums.CardTags.FROST)) {
                int newCost = c.cost - 2;
                if (newCost < 0 ) newCost = 0;
                c.setCostForTurn(newCost);
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
