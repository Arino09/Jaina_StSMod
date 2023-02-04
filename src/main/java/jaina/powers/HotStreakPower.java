package jaina.powers;

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

public class HotStreakPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("HotStreakPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HotStreakPower(AbstractCreature owner) {
        super(POWER_ID, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = -1;
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(JainaEnums.CardTags.FIRE)) {
            addToBot(new RestoreCostAction(JainaEnums.CardTags.FIRE));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void onInitialApplication() {
        flash();
        addToBot(new ReduceCostAction(JainaEnums.CardTags.FIRE));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RestoreCostAction(JainaEnums.CardTags.FIRE));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
