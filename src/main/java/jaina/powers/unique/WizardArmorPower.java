package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;
import jaina.powers.SpellForcePower;

public class WizardArmorPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("WizardArmorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int baseAmount;

    public WizardArmorPower(AbstractCreature owner, int amount) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
        this.owner = owner;
        this.baseAmount = this.amount = amount;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.baseAmount += stackAmount;
        this.amount = this.baseAmount;
    }

    @Override
    public void onInitialApplication() {
        onSpecificTrigger();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        onSpecificTrigger();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        onSpecificTrigger();
        addToBot(new GainBlockAction(owner, amount));
    }

    @Override
    public void onSpecificTrigger() {
        if (owner.hasPower(SpellForcePower.POWER_ID)) {
            amount = baseAmount + owner.getPower(SpellForcePower.POWER_ID).amount;
        } else {
            amount = baseAmount;
        }
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        onSpecificTrigger();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
