package jaina.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums.CardTags;

public class RubySpellstone extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("RubySpellstone");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final int AMT = 3;
    private AbstractCard.CardTags lastTag;

    public RubySpellstone() {
        super(ID, false, RELIC_STRINGS, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.hasTag(lastTag) && lastTag != null) {
            counter++;
            if (counter == AMT) {
                addToBot(new GainEnergyAction(1));
                counter = 0;
            }
        } else {
            updateTag(targetCard);
            if (lastTag != null) {
                counter = 1;
            } else {
                counter = 0;
            }
        }
    }

    private void updateTag(AbstractCard card) {
        if (card.hasTag(CardTags.ARCANE)) {
            lastTag = CardTags.ARCANE;
        } else if (card.hasTag(CardTags.FIRE)) {
            lastTag = CardTags.FIRE;
        } else if (card.hasTag(CardTags.FROST)) {
            lastTag = CardTags.FROST;
        } else {
            lastTag = null;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RubySpellstone();
    }

}