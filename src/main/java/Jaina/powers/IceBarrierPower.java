package Jaina.powers;

import Jaina.ModCore.IHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class IceBarrierPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("IceBarrierPower");
    public static final String POWER_ID_P = IHelper.makeID("IceBarrierPowerP");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final int BLOCK;

    public IceBarrierPower(AbstractCreature owner, boolean upgraded) {
        super(upgraded ? POWER_ID_P : POWER_ID, true, NAME, PowerType.BUFF);
        BLOCK = upgraded ? 16 : 12;
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
        flash();
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.owner, BLOCK));
        amount--;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
        return damageAmount;
    }

}
