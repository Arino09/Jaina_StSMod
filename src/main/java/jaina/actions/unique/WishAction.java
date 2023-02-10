package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class WishAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean upgraded;

    public WishAction(int amount, boolean upgraded) {
        p = AbstractDungeon.player;
        setValues(p, AbstractDungeon.player);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.amount = amount;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup library = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            for (AbstractCard c : CardLibrary.getCardList(JainaEnums.JAINA_LIBRARY)) {
                if (c.color == JainaEnums.JAINA_COLOR) {
                    if (upgraded) {
                        c.upgrade();
                    }
                    library.addToBottom(c);
                }
            }
            AbstractDungeon.gridSelectScreen.open(library, amount, IHelper.UI_STRINGS.TEXT[3], false);
            tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                c.setCostForTurn(0);
                c.isCostModifiedForTurn = true;
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
