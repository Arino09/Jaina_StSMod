package jaina.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ArcaniteCrystal extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("ArcaniteCrystal");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final Logger logger = LogManager.getLogger(ArcaniteCrystal.class.getName());

    public ArcaniteCrystal() {
        super(ID, false, RELIC_STRINGS, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart() {
        this.usedUp = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        // 使用奥术牌且为第一次触发
        if (card.hasTag(JainaEnums.CardTags.ARCANE) && !this.usedUp) {
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            ArrayList<AbstractCard> groupCopy = new ArrayList<>();
            for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
                if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce) {
                    groupCopy.add(abstractCard);
                    continue;
                }
                logger.info("COST IS 0: " + abstractCard.name);
            }
            for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
                if (i.card != null) {
                    logger.info("INVALID: " + i.card.name);
                    groupCopy.remove(i.card);
                }
            }
            AbstractCard c = null;
            if (!groupCopy.isEmpty()) {
                logger.info("VALID CARDS: ");
                for (AbstractCard cc : groupCopy)
                    logger.info(cc.name);
                c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
            } else {
                logger.info("NO VALID CARDS");
            }
            if (c != null) {
                logger.info("Arcanite crystal: " + c.name);
                c.setCostForTurn(0);
            } else {
                logger.info("ERROR: ARCANITE CRYSTAL NOT WORKING");
            }
            this.usedUp = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArcaniteCrystal();
    }
}