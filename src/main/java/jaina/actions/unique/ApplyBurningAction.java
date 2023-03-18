package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import jaina.powers.BurningPower;
import jaina.powers.unique.FlameWardPower;
import jaina.powers.unique.WildfirePower;

public class ApplyBurningAction extends AbstractGameAction {

    private final AbstractCreature source;
    private final AbstractCreature target;

    public ApplyBurningAction(AbstractCreature source, AbstractCreature target, int amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.actionType = ActionType.DEBUFF;
    }

    @Override
    public void update() {
        if (source.isPlayer && !target.isDying && !target.halfDead && !target.isDead) {
            if (source.hasPower(WildfirePower.POWER_ID)) {
                int extra = source.getPower(WildfirePower.POWER_ID).amount;
                amount += extra;
            }
            addToBot(new VFXAction(new FireBurstParticleEffect(target.hb_x, target.hb_y)));
            addToBot(new ApplyPowerAction(target, source, new BurningPower(target, amount)));
            if (source.hasPower(FlameWardPower.POWER_ID)) {
                int block = source.getPower(FlameWardPower.POWER_ID).amount;
                addToBot(new GainBlockAction(source, Math.abs(block * amount)));
            }
        }
        this.isDone = true;
    }
}
