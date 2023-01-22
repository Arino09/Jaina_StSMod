package Jaina.powers;

import Jaina.ModCore.IHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class VaporizePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("VaporizePower");
    public static final String POWER_ID_P = IHelper.makeID("VaporizePowerP");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final int DAMAGE;

    public VaporizePower(AbstractCreature owner, boolean upgraded) {
        super(upgraded ? POWER_ID_P : POWER_ID, true, NAME, PowerType.BUFF);
        DAMAGE = upgraded ? 9 : 6;
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

        // 先写减少层数效果，因为用的addToTop方法
        if (amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
        // 受到攻击时对目标造成伤害
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            flash();
            AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, DAMAGE)));
            amount--;
            if(info.owner.isDying || info.owner.currentHealth <= 0 && !info.owner.halfDead) {
                return 0;
            }
        }
        return damageAmount;
    }


}
