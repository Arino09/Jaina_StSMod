package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;

public class IceBlockPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("IceBlockPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IceBlockPower(AbstractCreature owner) {
        super(POWER_ID, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = -1;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            if (owner.currentHealth + owner.currentBlock <= damageAmount) {
                flash();
                addToBot(new VFXAction(new FlashAtkImgEffect(owner.hb_x, owner.hb_y, AttackEffect.SHIELD)));
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                return 0;
            }
        }
        return damageAmount;
    }
}
