package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import jaina.powers.BurningPower;

public class ForbiddenFlameAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean freeToPlayOnce;
    private final int energyOnUse;
    private int damage;

    public ForbiddenFlameAction(AbstractPlayer p, AbstractMonster m, int damage, int burningAmt, boolean freeToPlayOnce, int energyOnUse) {
        this.amount = burningAmt;
        this.damage = damage;
        this.p = p;
        this.target = m;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
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
            damage *= effect;
            amount *= effect;
            addToBot(new DamageAction(target, new DamageInfo(p, this.damage), AttackEffect.FIRE));
            addToBot(new ApplyPowerAction(target, p, new BurningPower(target, amount)));
            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
