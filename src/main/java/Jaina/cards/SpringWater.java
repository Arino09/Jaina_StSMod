package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpringWater extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("SpringWater");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public SpringWater() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE);
        setMagicNumber(5);
    }

    @Override
    public void upp() {
        updateCost(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber, new SpringWaterAction()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpringWater();
    }

    private class SpringWaterAction extends AbstractGameAction {

        public SpringWaterAction() {
            this.duration = 0.0F;
            this.actionType = ActionType.WAIT;
        }

        @Override
        public void update() {
            int cnt = 0;
            // 每当抽到技能牌时获得1点能量值，最多回3
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.type == CardType.SKILL) {
                    AbstractDungeon.player.gainEnergy(1);
                    cnt++;
                    if(cnt >= 3) break;
                }
            }
            this.isDone = true;
        }
    }
}
