package Jaina.powers;

import Jaina.ModCore.IHelper;
import Jaina.cards.AbstractJainaCard;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

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
        upgradeSpellDamage(amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "SpellDamagePower"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;

        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    private void upgradeSpellDamage(int amount) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            IHelper.spellDamageApply((AbstractJainaCard)c, amount);
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            IHelper.spellDamageApply((AbstractJainaCard)c, amount);
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            IHelper.spellDamageApply((AbstractJainaCard)c, amount);
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            IHelper.spellDamageApply((AbstractJainaCard)c, amount);
        }
    }
}
