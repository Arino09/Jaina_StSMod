package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.cards.Simulacrum;
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
            // 抽牌堆没有牌直接结束
            if (draw.size() == 0) {
                this.isDone = true;
                return;
            }
            // 抽牌堆只有一张牌直接复制
            if (draw.size() == 1) {
                AbstractCard card = draw.getTopCard().makeSameInstanceOf();
                updateCard(card);
                // 如果手牌满了则进入弃牌堆并提示
                if (p.hand.size() == 10) {
                    card.moveToDiscardPile();
                    p.createHandIsFullDialog();
                } else {
                    // 否则正常加入手牌
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
                AbstractCard card = c.makeSameInstanceOf();
                updateCard(card);
                // 如果手牌满了则进入弃牌堆并提示
                if (p.hand.size() == 10) {
                    card.moveToDiscardPile();
                    p.createHandIsFullDialog();
                } else {
                    // 否则正常加入手牌
                    p.hand.addToTop(card);
                }
                p.hand.refreshHandLayout();
                p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            p.hand.refreshHandLayout();
            this.isDone = true;
        }
    }

    // 在原卡的基础上加上【消耗】并更新卡牌描述
    private void updateCard(AbstractCard card) {
        card.name = card.name + Simulacrum.CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        if (!card.exhaust) {
            card.exhaust = true;
            card.rawDescription = card.rawDescription + Simulacrum.CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            card.initializeDescription();
        }
    }
}
