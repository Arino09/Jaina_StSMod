package jaina.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.actions.SpellDamageAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class SpellDamagePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("SpellDamagePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SpellDamagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
        updateDescription();
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }
        addToBot(new SpellDamageAction());
    }

    @Override
    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;

        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        addToBot(new SpellDamageAction());
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        addToBot(new SpellDamageAction());
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float finalDamage = damage;
        if (IHelper.isSpellDamage(type)) {
            finalDamage = damage + amount;
        } else if (type.equals(JainaEnums.DamageType.ARCANE_BLAST)) {
            finalDamage = damage + 2 * amount;
        } else if (type.equals(JainaEnums.DamageType.ARCANE_BLAST_P)) {
            finalDamage = damage + 3 * amount;
        }
        return finalDamage;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
