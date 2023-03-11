package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class DragonsBreath extends AbstractFireCard {

    public static final String ID = IHelper.makeID("DragonsBreath");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;
    private int lastCost = COST;

    public DragonsBreath() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(14);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new DragonsBreath();
    }

    // 减费时显示金色框
    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (hasOnlyFire()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        changeCost();
    }

    private boolean hasOnlyFire() {
        boolean onlyFire = true;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.hasTag(JainaEnums.CardTags.FIRE)) {
                onlyFire = false;
                break;
            }
        }
        return onlyFire;
    }

    private void changeCost() {
        // 龙息术减费优先级高于其他减费，若不为0费还是会减到0费
        if (hasOnlyFire()) {
            if (!isCostModifiedForTurn || costForTurn != 0) {
                lastCost = costForTurn;
                setCostForTurn(0);
                logger.info(this.cardID + "cost reduced.");
            }
            // 如果被其他卡牌减到0费则直接结束判断
        } else {
            // 如果不能减费则恢复到之前的耗能
            setCostForTurn(lastCost);
            if (costForTurn == COST) {
                this.isCostModifiedForTurn = false;
            }
        }
    }
}
