package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.cards.ExplosiveRunes;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;

public class ExplosiveRunesPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("ExplosiveRunesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int postfix = 0;

    public ExplosiveRunesPower(AbstractCreature owner, int damage) {
        super(POWER_ID + postfix++, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = damage;
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS
                && info.owner != null && info.owner != this.owner) {
            flash();
            addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.FIRE, true));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            addToBot(new MakeTempCardInDiscardAction(new ExplosiveRunes(), 1));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
