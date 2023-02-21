package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class RestoreCostAction extends AbstractGameAction {

    private final boolean isFrostAffinity;

    /**
     * 恢复费用buff
     *
     * @param isFrostAffinity 是否是冰霜亲和
     */
    public RestoreCostAction(boolean isFrostAffinity) {
        this.isFrostAffinity = isFrostAffinity;
    }

    private void restoreCost(ArrayList<AbstractCard> cards, boolean isFrostAffinity) {
        // 冰霜亲和牌恢复
        if (isFrostAffinity) {
            for (AbstractCard c : cards) {
                if (c.hasTag(JainaEnums.CardTags.AFFINITY) && c.hasTag(JainaEnums.CardTags.FROST)) {
                    c.setCostForTurn(c.cost);
                    c.isCostModifiedForTurn = false;
                }
            }
        } else {
            // 咒术洪流牌恢复
            for (AbstractCard c : cards) {
                if (c.hasTag(JainaEnums.CardTags.INCANTER) && !c.hasTag(JainaEnums.CardTags.AFFINITY)) {
                    c.setCostForTurn(c.cost);
                    c.freeToPlayOnce = false;
                    c.isCostModifiedForTurn = false;
                }
            }
        }
    }

    @Override
    public void update() {
        restoreCost(AbstractDungeon.player.hand.group, isFrostAffinity);
        restoreCost(AbstractDungeon.player.discardPile.group, isFrostAffinity);
        restoreCost(AbstractDungeon.player.drawPile.group, isFrostAffinity);
        restoreCost(AbstractDungeon.player.exhaustPile.group, isFrostAffinity);
        this.isDone = true;
    }
}
