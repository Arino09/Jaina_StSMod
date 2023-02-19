package jaina.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import jaina.modCore.IHelper;

public class RubySpellstone extends AbstractJainaRelic implements ClickableRelic {
    public static final String ID = IHelper.makeID("RubySpellstone");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public RubySpellstone() {
        super(ID, false, RELIC_STRINGS, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        usedUp = false;
    }

    @Override
    public void onRightClick() {
        if (!usedUp) {
            AbstractCard card = IHelper.generateRandomJainaCards(1, true, true, true, false, true).get(0);
            IHelper.getTempCard(card);
            usedUp = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RubySpellstone();
    }


}