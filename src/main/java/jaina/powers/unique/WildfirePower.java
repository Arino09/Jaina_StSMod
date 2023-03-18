package jaina.powers.unique;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;

public class WildfirePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("WildfirePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WildfirePower(AbstractCreature owner, int amount) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
