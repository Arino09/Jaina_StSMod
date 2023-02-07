package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.IHelper;

public class ExhaustCardFromDrawAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public ExhaustCardFromDrawAction(int amount) {
        p = AbstractDungeon.player;
        setValues(p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup draw = p.drawPile;
            if (draw.size() == 0) {
                this.isDone = true;
                return;
            }
            if (draw.size() == 1) {
                AbstractCard card = draw.getTopCard();
                card.exhaust = true;
                if (p.hand.size() == 10) {
                    return;
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    for (int i = 0; i < amount; i++) {
                        AbstractDungeon.player.hand.addToTop(card);
                    }
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(draw, amount, IHelper.UI_STRINGS.TEXT[3], false);
            tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                c.exhaust = true;
                if (p.hand.size() == 10) {
                    c.moveToDiscardPile();
                    p.createHandIsFullDialog();
                } else {
                    p.hand.addToTop(c);
                }
                p.hand.refreshHandLayout();
                p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            p.hand.refreshHandLayout();
            this.isDone = true;
        }
    }
}
