package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.cards.Duplicate;
import jaina.modCore.IHelper;

import java.util.ArrayList;

public class DuplicateAction extends AbstractGameAction {
    private static final float DURATION_PER_CARD = 0.25F;
    private final ArrayList<AbstractCard> cannotDuplicate = new ArrayList<>();
    private final AbstractPlayer p;
    private final int dupeAmount;

    public DuplicateAction(AbstractCreature source, int amount) {
        setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.DRAW;
        this.duration = DURATION_PER_CARD;
        this.p = AbstractDungeon.player;
        this.dupeAmount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                if (!isDuplicatable(c))
                    this.cannotDuplicate.add(c);
            }
            if (this.cannotDuplicate.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotDuplicate.size() == dupeAmount)
                for (AbstractCard c : this.p.hand.group) {
                    if (isDuplicatable(c)) {
                        for (int i = 0; i < this.dupeAmount; i++)
                            IHelper.getTempCard(c.makeStatEquivalentCopy());
                        this.isDone = true;
                        return;
                    }
                }
            this.p.hand.group.removeAll(this.cannotDuplicate);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(IHelper.UI_STRINGS.TEXT[3], 1, false, false, false, false);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1) {
                for (int i = 0; i < this.dupeAmount; i++)
                    IHelper.getTempCard(p.hand.getTopCard().makeStatEquivalentCopy());
                returnCards();
                this.isDone = true;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                IHelper.getTempCard(c.makeStatEquivalentCopy());
                for (int i = 0; i < this.dupeAmount; i++)
                    IHelper.getTempCard(c.makeStatEquivalentCopy());
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotDuplicate)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }

    private boolean isDuplicatable(AbstractCard card) {
        return (!card.cardID.equals(Duplicate.ID));
    }
}
