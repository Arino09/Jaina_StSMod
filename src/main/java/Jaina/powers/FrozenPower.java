package Jaina.powers;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;

import java.lang.reflect.Field;

public class FrozenPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("FrozenPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private byte moveByte;
    private Intent moveIntent;
    private EnemyMoveInfo move;

    public FrozenPower(AbstractMonster owner) {
        super(POWER_ID, false, NAME, PowerType.DEBUFF);
        this.owner = owner;
        this.isTurnBased = true;
        this.amount = -1;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfRound() {
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                if (FrozenPower.this.owner instanceof AbstractMonster) {
                    FrozenPower.this.moveByte = ((AbstractMonster) FrozenPower.this.owner).nextMove;
                    FrozenPower.this.moveIntent = ((AbstractMonster) FrozenPower.this.owner).intent;

                    try {
                        Field f = AbstractMonster.class.getDeclaredField("move");
                        f.setAccessible(true);
                        FrozenPower.this.move = (EnemyMoveInfo) f.get(FrozenPower.this.owner);

                        EnemyMoveInfo frozenMove = new EnemyMoveInfo(FrozenPower.this.moveByte,
                                JainaEnums.FROZEN, -1, 0, false);
                        f.set(FrozenPower.this.owner, frozenMove);
                        ((AbstractMonster) FrozenPower.this.owner).createIntent();
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                this.isDone = true;
            }
        });
    }

    public void onRemove() {
        if (this.owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster) this.owner;
            if (this.move != null) {
                m.setMove(this.moveByte, this.moveIntent, this.move.baseDamage, this.move.multiplier, this.move.isMultiDamage);
            } else {
                m.setMove(this.moveByte, this.moveIntent);
            }
            m.createIntent();
            m.applyPowers();
        }
    }
}
