package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.cards.ApexisBlast;
import jaina.cards.CramSession;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellForcePower;
import jaina.powers.unique.WizardArmorPower;

import java.util.ArrayList;

public class SpellForceAction extends AbstractGameAction {

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(SpellForcePower.POWER_ID)) {
            int forceAmt = AbstractDungeon.player.getPower(SpellForcePower.POWER_ID).amount;
            // 更新所有地方的卡牌描述
            updateMagicCards(AbstractDungeon.player.hand.group, forceAmt);
            updateMagicCards(AbstractDungeon.player.discardPile.group, forceAmt);
            updateMagicCards(AbstractDungeon.player.drawPile.group, forceAmt);
            updateMagicCards(AbstractDungeon.player.exhaustPile.group, forceAmt);
            // 更新法师护甲
            if (AbstractDungeon.player.hasPower(WizardArmorPower.POWER_ID)) {
                AbstractDungeon.player.getPower(WizardArmorPower.POWER_ID).onSpecificTrigger();
            }
        }
        this.isDone = true;
    }

    /**
     * 更新M值受法术伤害影响的卡牌描述
     *
     * @param cards  卡牌堆
     * @param amount 法术伤害
     */
    private void updateMagicCards(ArrayList<AbstractCard> cards, int amount) {
        for (AbstractCard c : cards) {
            if (c instanceof CramSession || c instanceof ApexisBlast) {
                c.magicNumber = c.baseMagicNumber + amount;
                c.initializeDescription();
            }
            if (c.color == JainaEnums.JAINA_COLOR && c.baseBlock > 0 && !c.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
                c.block = c.baseBlock + amount;
                c.initializeDescription();
            }
        }
    }
}
