package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class FrostAffinityStartAction extends AbstractGameAction {

    private static void restoreCost(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.hasTag(JainaEnums.CardTags.FROST)) {
                System.out.println(c.cost);
                int newCost = c.cost - 2;
                if (newCost < 0) newCost = 0;
                c.setCostForTurn(newCost);
                System.out.println(c.costForTurn);
            }
        }
    }

    @Override
    public void update() {
        restoreCost(AbstractDungeon.player.hand.group);
        restoreCost(AbstractDungeon.player.discardPile.group);
        restoreCost(AbstractDungeon.player.drawPile.group);
        restoreCost(AbstractDungeon.player.exhaustPile.group);
        this.isDone = true;
    }
}
