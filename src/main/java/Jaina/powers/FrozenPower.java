package Jaina.powers;

import Jaina.ModCore.IHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;

public class FrozenPower extends AbstractPower {
    public static final String POWER_ID = IHelper.makeID("Frozen");
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private EnemyMoveInfo move;

    public FrozenPower(AbstractMonster owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.updateDescription();
        //this.img = ImageMaster.loadImage("Jaina/img/powers/test32.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("Jaina/img/powers/test32.png"),0,0,32,32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("Jaina/img/powers/test84.png"),0,0,84,84);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    
    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
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
                        FrozenPower.this.move = (EnemyMoveInfo)f.get(FrozenPower.this.owner);
                        EnemyMoveInfo stunMove = new EnemyMoveInfo(FrozenPower.this.moveByte, AbstractMonster.Intent.STUN, -1, 0, false);
                        f.set(FrozenPower.this.owner, stunMove);
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
            AbstractMonster m = (AbstractMonster)this.owner;
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
