package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.actions.ReduceCostAction;
import jaina.actions.RestoreCostAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.AbstractJainaPower;

public class FrostAffinityPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("FrostAffinityPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrostAffinityPower(AbstractCreature owner) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = 1;
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(JainaEnums.CardTags.FROST)) {
            addToBot(new RestoreCostAction(true));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void onInitialApplication() {
        flash();
        addToBot(new ReduceCostAction(true));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RestoreCostAction(true));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
