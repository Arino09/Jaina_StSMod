package jaina.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.BurningPower;

import java.util.ArrayList;

public class CinderstormAction extends AbstractGameAction {

    public CinderstormAction(int burningAmt) {
        this.startDuration = this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.amount = burningAmt;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        // 构造灼烧牌列表
        for (AbstractCard c : p.hand.group) {
            if (c instanceof Burn) {
                cardsToExhaust.add(c);
            }
        }
        // 每灼烧一张给予所有敌人amount层灼烧
        for (AbstractCard c : cardsToExhaust) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                addToTop(new ApplyPowerAction(m, p, new BurningPower(m, amount)));
            }
        }
        // 先消耗所有灼烧牌
        for (AbstractCard c : cardsToExhaust) {
            addToTop(new ExhaustSpecificCardAction(c, p.hand));
        }
        this.isDone = true;
    }

}
