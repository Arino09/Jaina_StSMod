package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ReduceCostAction extends AbstractGameAction {

    public final CardTags tags;

    public ReduceCostAction(CardTags tags) {
        this.tags = tags;
    }

    private void reduceCost(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.hasTag(tags)) {
                int newCost = c.cost - 2;
                if (newCost < 0) newCost = 0;
                c.setCostForTurn(newCost);
            }
        }
    }

    @Override
    public void update() {
        reduceCost(AbstractDungeon.player.hand.group);
        reduceCost(AbstractDungeon.player.discardPile.group);
        reduceCost(AbstractDungeon.player.drawPile.group);
        reduceCost(AbstractDungeon.player.exhaustPile.group);
        this.isDone = true;
    }
}
