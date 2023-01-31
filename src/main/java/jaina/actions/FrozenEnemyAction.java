package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.FrozenPower;

public class FrozenEnemyAction extends AbstractGameAction {
    public FrozenEnemyAction(AbstractMonster target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.amount = 1;
        this.actionType = ActionType.DEBUFF;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new FrozenPower((AbstractMonster) this.target)));
        }
        this.tickDuration();
    }
}
