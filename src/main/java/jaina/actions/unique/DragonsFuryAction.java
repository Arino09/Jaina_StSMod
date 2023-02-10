package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class DragonsFuryAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean freeToPlayOnce;
    private final int energyOnUse;
    //private final int burnAmt;

    public DragonsFuryAction(AbstractPlayer p, int damage, boolean freeToPlayOnce, int energyOnUse) {
        this.amount = damage;
        //this.burnAmt = burnAmt;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.DAMAGE;
        this.energyOnUse = energyOnUse;
    }


    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                addToBot(new DamageAllEnemiesAction(p, this.amount,
                        DamageInfo.DamageType.NORMAL, AttackEffect.FIRE));
                IHelper.getBurn(1);
            }
            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
