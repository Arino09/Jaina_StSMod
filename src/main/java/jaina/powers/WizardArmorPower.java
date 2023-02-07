package jaina.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jaina.modCore.IHelper;

public class WizardArmorPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("WizardArmorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int spell = 0;

    public WizardArmorPower(AbstractCreature owner, int amount) {
        super(POWER_ID, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (owner.isPlayer && owner.hasPower(SpellDamagePower.POWER_ID)) {
            spell = owner.getPower(SpellDamagePower.POWER_ID).amount;
        }
        addToBot(new GainBlockAction(owner, amount + spell));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (owner.isPlayer && owner.hasPower(SpellDamagePower.POWER_ID)) {
            spell = owner.getPower(SpellDamagePower.POWER_ID).amount;
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (amount + spell) + DESCRIPTIONS[1];
    }

}
