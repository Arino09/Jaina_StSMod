package jaina.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.actions.ReduceCostAction;
import jaina.actions.RestoreCostAction;
import jaina.modCore.IHelper;

public class RobesOfGaudiness extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("RobesOfGaudiness");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public RobesOfGaudiness() {
        super(ID, false, RELIC_STRINGS, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStart() {
        // 回合开始时重置使用信息和减费
        this.setCounter(0);
        addToBot(new ReduceCostAction(false));
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        if (this.counter < 3) {
            return true;
        } else {
            // 不能打出牌时显示提示信息
            card.cantUseMessage = IHelper.UI_STRINGS.TEXT[4];
            return false;
        }
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (this.counter < 3){
            // 遗物计数小于3则打牌并且更新计数
            addToBot(useCardAction);
            counter++;
            if (counter >= 3) {
                addToBot(new RestoreCostAction(false));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RobesOfGaudiness();
    }
}