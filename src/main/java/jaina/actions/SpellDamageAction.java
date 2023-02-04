package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.cards.CramSession;
import jaina.powers.SpellDamagePower;

import java.util.ArrayList;

public class SpellDamageAction extends AbstractGameAction {

    @Override
    public void update() {
        int amount;
        if (AbstractDungeon.player.hasPower(SpellDamagePower.POWER_ID)) {
            amount = AbstractDungeon.player.getPower(SpellDamagePower.POWER_ID).amount;
            updateMagicNumber(AbstractDungeon.player.hand.group, amount);
            updateMagicNumber(AbstractDungeon.player.discardPile.group, amount);
            updateMagicNumber(AbstractDungeon.player.drawPile.group, amount);
            updateMagicNumber(AbstractDungeon.player.exhaustPile.group, amount);
        }
        this.isDone = true;
    }

    private void updateMagicNumber(ArrayList<AbstractCard> cards, int amount) {
        for (AbstractCard c : cards) {
            if (c instanceof CramSession) {
                c.magicNumber = c.baseMagicNumber + amount;
            }
        }
    }
}
