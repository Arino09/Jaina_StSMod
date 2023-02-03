package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class RestoreCostAction extends AbstractGameAction {

    public final CardTags tags;

    public RestoreCostAction(CardTags tags) {
        this.tags = tags;
    }

    private void restoreCost(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.hasTag(tags) && c.isCostModifiedForTurn) {
                System.out.println(c.cost);
                c.setCostForTurn(c.cost);
                c.isCostModifiedForTurn = false;
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
