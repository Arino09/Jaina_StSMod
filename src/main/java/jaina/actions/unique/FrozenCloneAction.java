package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class FrozenCloneAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean freeToPlayOnce;
    private final int energyOnUse;
    private final boolean upgraded;

    public FrozenCloneAction(AbstractPlayer p, int amount, boolean freeToPlayOnce, int energyOnUse, boolean upgraded) {
        this.amount = amount;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.BLOCK;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
    }


    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) effect = this.energyOnUse;

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (upgraded) effect += 1;

        if (effect > 0) {
            for (int i = 0; i < effect; i++) addToBot(new GainBlockAction(p, p, this.amount));
            if (!freeToPlayOnce) p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
