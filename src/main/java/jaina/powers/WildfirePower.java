package jaina.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jaina.modCore.IHelper;

public class WildfirePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("WildfirePower");
    public static final String POWER_ID_P = IHelper.makeID("WildfirePowerP");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final boolean upgraded;

    public WildfirePower(AbstractCreature owner, boolean upgraded) {
        super(upgraded ? POWER_ID_P : POWER_ID, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = -1;
        this.upgraded = upgraded;
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof BurningPower && !target.isPlayer) {
            if (upgraded) {
                power.amount *= 2;
            } else {
                power.amount += 3;
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
