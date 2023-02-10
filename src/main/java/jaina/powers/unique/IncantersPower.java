package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.actions.ReduceCostAction;
import jaina.actions.RestoreCostAction;
import jaina.modCore.IHelper;
import jaina.powers.AbstractJainaPower;

import static jaina.modCore.Core.MOD_ID;

public class IncantersPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("IncantersPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int cards;

    public IncantersPower(AbstractCreature owner) {
        super(POWER_ID, true, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = 1;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        cards = 0; // 第一回合不可能减费
    }

    @Override
    public void atStartOfTurn() {
        flash();
        cards = amount;
        addToBot(new ReduceCostAction(false));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.cardID.startsWith(MOD_ID + ":") && !card.isStarterStrike() && !card.isStarterDefend()) {
            cards--;
            if (cards <= 0) {
                addToBot(new RestoreCostAction(false));
            }
        }
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new RestoreCostAction(true));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

}
