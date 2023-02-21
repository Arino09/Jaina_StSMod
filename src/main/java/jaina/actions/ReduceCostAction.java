package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class ReduceCostAction extends AbstractGameAction {

    private final boolean isFrostAffinity;

    /**
     * 减少2费buff
     * @param isFrostAffinity 是否是冰霜亲和
     */
    public ReduceCostAction(boolean isFrostAffinity) {
        this.isFrostAffinity = isFrostAffinity;
    }

    private void reduceCost(ArrayList<AbstractCard> cards, boolean isFrostAffinity) {
        // 冰霜亲和减费部分
        if (isFrostAffinity) {
            for (AbstractCard c : cards) {
                if (c.hasTag(JainaEnums.CardTags.FROST)) {
                    int newCost = c.cost - 2;
                    if (newCost < 0) newCost = 0;
                    c.setCostForTurn(newCost);
                    c.tags.add(JainaEnums.CardTags.AFFINITY);
                }
            }
        } else {
            // 咒术洪流减费部分
            for (AbstractCard c : cards) {
                if (c.cardID.startsWith("jaina:") && !c.isStarterStrike() && !c.isStarterDefend()) {
                    c.setCostForTurn(0);
                    c.freeToPlayOnce = true;
                    c.tags.add(JainaEnums.CardTags.INCANTER);
                }
            }
        }
    }

    @Override
    public void update() {
        reduceCost(AbstractDungeon.player.hand.group, isFrostAffinity);
        reduceCost(AbstractDungeon.player.discardPile.group, isFrostAffinity);
        reduceCost(AbstractDungeon.player.drawPile.group, isFrostAffinity);
        reduceCost(AbstractDungeon.player.exhaustPile.group, isFrostAffinity);
        this.isDone = true;
    }
}
