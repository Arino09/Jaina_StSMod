package jaina.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import jaina.modCore.IHelper;

public class CombustionPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("CombustionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CombustionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, false, NAME, PowerType.DEBUFF);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        addToBot(new VFXAction(new FireBurstParticleEffect(owner.hb.x, owner.hb.y)));
        // 如果目标具有冻结则移除冻结
        if (owner.hasPower(FrozenPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, owner.getPower(FrozenPower.POWER_ID)));
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(FrozenPower.POWER_ID)) {
            // 给予冻结时移除燃烧
            addToBot(new RemoveSpecificPowerAction(target, target, this));
        }
    }

    // 可以由外部触发
    @Override
    public void onSpecificTrigger() {
        if (!owner.isDying && !owner.isDeadOrEscaped()) {
            addToBot(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.FIRE));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    //  回合结束时自动触发
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        if (!isPlayer && !owner.isPlayer || isPlayer && owner.isPlayer) {
            onSpecificTrigger();
        }
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
