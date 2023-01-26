package Jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SpringWaterAction extends AbstractGameAction {

    public SpringWaterAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        int cnt = 0;
        // 每当抽到技能牌时获得1点能量值，最多回3
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.type == AbstractCard.CardType.SKILL) {
                AbstractDungeon.player.gainEnergy(1);
                cnt++;
                if (cnt >= 3) break;
            }
        }
        this.isDone = true;
    }
}
