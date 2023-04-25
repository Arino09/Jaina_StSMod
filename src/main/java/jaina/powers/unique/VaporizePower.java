package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.actions.ApplyBurningAction;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;


public class VaporizePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("VaporizePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VaporizePower(AbstractCreature owner) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = 1;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        // 受到攻击时对目标造成伤害
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            flash();
            addToTop(new ApplyBurningAction(this.owner, info.owner, info.output));
            amount--;
            // 层数为0后立马解除能力
            if (amount == 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            if (info.owner.isDying || info.owner.currentHealth <= 0 && !info.owner.halfDead) {
                return 0;
            }
        }
        return damageAmount;
    }

}
