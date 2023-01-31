package jaina.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.actions.IncantersEndAction;
import jaina.actions.IncantersStartAction;
import jaina.modCore.IHelper;

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
    public void atStartOfTurn() {
        // 所有jaina法术变为0费
        flash();
        cards = amount;
        addToBot(new IncantersStartAction());
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.cardID.startsWith(MOD_ID + ":") && !card.isStarterStrike() && !card.isStarterDefend()) {
            cards--;
            if (cards <= 0) {
                addToBot(new IncantersEndAction());
            }
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
