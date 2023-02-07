package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.cards.ApexisBlast;
import jaina.cards.CramSession;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellDamagePower;

import java.util.ArrayList;

public class SpellDamageAction extends AbstractGameAction {

    @Override
    public void update() {
        int amount;
        if (AbstractDungeon.player.hasPower(SpellDamagePower.POWER_ID)) {
            amount = AbstractDungeon.player.getPower(SpellDamagePower.POWER_ID).amount;
            updateMagicCards(AbstractDungeon.player.hand.group, amount);
            updateMagicCards(AbstractDungeon.player.discardPile.group, amount);
            updateMagicCards(AbstractDungeon.player.drawPile.group, amount);
            updateMagicCards(AbstractDungeon.player.exhaustPile.group, amount);
        }
        this.isDone = true;
    }

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
