package jaina.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.actions.SpellDamageAction;
import jaina.cards.ApexisBlast;
import jaina.cards.ArcaneBlast;
import jaina.cards.CramSession;
import jaina.cards.FontOfPower;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.unique.WizardArmorPower;

public class SpellForcePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("SpellForcePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SpellForcePower(AbstractCreature owner, int amount) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
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
        if (AbstractDungeon.player.hasPower(WizardArmorPower.POWER_ID)) {
            AbstractDungeon.player.getPower(WizardArmorPower.POWER_ID).onSpecificTrigger();
        }
    }

    @Override
    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;

        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        addToBot(new SpellDamageAction());
        if (AbstractDungeon.player.hasPower(WizardArmorPower.POWER_ID)) {
            AbstractDungeon.player.getPower(WizardArmorPower.POWER_ID).onSpecificTrigger();
        }
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.color == JainaEnums.JAINA_COLOR && !card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
            return blockAmount + this.amount;
        }
        return blockAmount;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        String id = card.cardID;
        if (id.equals(CramSession.ID) || id.equals(FontOfPower.ID) || id.equals(ApexisBlast.ID)) {
            card.magicNumber += this.amount;
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        float finalDamage = damage;
        if (card.cardID.equals(ArcaneBlast.ID)) {
            finalDamage = damage + 2 * amount;
            if (card.upgraded) {
                finalDamage += amount;
            }
        } else if (IHelper.isSpellDamage(card)) {
            finalDamage = damage + amount;
        }
        return super.atDamageGive(finalDamage, type, card);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }
}
