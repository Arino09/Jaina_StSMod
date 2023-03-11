package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;

public class SplittingImagePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("SplittingImagePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SplittingImagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damage) {

        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            flash();
            addToBot(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, amount)));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
        return damage;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
