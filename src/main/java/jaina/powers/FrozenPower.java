package jaina.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.lang.reflect.Field;

public class FrozenPower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("FrozenPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private byte moveByte;
    private Intent moveIntent;
    private EnemyMoveInfo move;

    public FrozenPower(AbstractMonster owner, int amount) {
        super(POWER_ID, false, NAME, PowerType.DEBUFF);
        this.owner = owner;
        this.isTurnBased = true;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        // 最大只能叠加3层，叠到3层后触发冻结效果
        if (this.amount >= 3) {
            this.amount = 3;
            addToBot(new FreezeAction());
        }
    }

    @Override
    public void onInitialApplication() {
        if (this.amount >= 3) {
            this.amount = 3;
            addToBot(new FreezeAction());
        }
        if (this.owner.hasPower(BurningPower.POWER_ID)) {
            // 给予冻结时移除燃烧
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, owner.getPower(BurningPower.POWER_ID)));
        }
    }

    // 1层及以上时触发减伤效果
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && this.amount >= 1) {
            return damage * 0.75F;
        }
        return damage;
    }

    // 2层及以上时触发增伤效果
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL && this.amount >= 2) {
            return damage * 1.25F;
        }
        return damage;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount >= 2) {
            this.description += DESCRIPTIONS[1];
        }
        if (amount == 3) {
            this.description += DESCRIPTIONS[2];
        }
        this.description += (DESCRIPTIONS[3] + DESCRIPTIONS[4]);
    }

    @Override
    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onRemove() {
        if (this.owner instanceof AbstractMonster && this.amount == 3) {
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

    private class FreezeAction extends AbstractGameAction {
        @Override
        public void update() {
            if ((FrozenPower.this.owner instanceof AbstractMonster) &&
                    (((AbstractMonster) FrozenPower.this.owner).intent != JainaEnums.FROZEN)) {
                moveByte = ((AbstractMonster) owner).nextMove;
                moveIntent = ((AbstractMonster) owner).intent;
                try {
                    Field f = AbstractMonster.class.getDeclaredField("move");
                    f.setAccessible(true);
                    move = (EnemyMoveInfo) f.get(owner);
                    EnemyMoveInfo frozenMove = new EnemyMoveInfo(moveByte, JainaEnums.FROZEN, -1, 0, false);
                    f.set(owner, frozenMove);
                    ((AbstractMonster) owner).createIntent();
                    logger.info(owner.name + " has been frozen.");
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            this.isDone = true;
        }
    }
}
