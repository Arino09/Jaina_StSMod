package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;


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
        // 受到攻击时对目标造成伤害
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            flash();
            addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, DAMAGE,
                    DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            amount--;
            // 层数为0后立马解除能力
            if (amount <= 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            if (info.owner.isDying || info.owner.currentHealth <= 0 && !info.owner.halfDead) {
                return 0;
            }
        }
        return damageAmount;
    }


}
