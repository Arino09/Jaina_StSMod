package jaina.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.actions.JainaDiscoveryAction;
import jaina.modCore.Core;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class ArcanePotion extends AbstractPotion {
    public static final String ID = IHelper.makeID("ArcanePotion");
    public static final Color LIQUID_COLOR = CardHelper.getColor(99, 33, 183);
    public static final Color HYBRID_COLOR = CardHelper.getColor(221, 103, 237);
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);

    public ArcanePotion() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.COMMON, PotionSize.CARD, PotionEffect.NONE, LIQUID_COLOR, HYBRID_COLOR, null);
        this.labOutlineColor = Core.COLOR;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(JainaEnums.JAINA_LIBRARY)) {
            if (c.hasTag(JainaEnums.CardTags.ARCANE) && !c.rarity.equals(AbstractCard.CardRarity.BASIC)) {
                cards.add(c);
            }
        }
        for (AbstractCard c : cards) {
            c.setCostForTurn(0);
            c.freeToPlayOnce = true;
        }
        for (int i = 0; i < getPotency(); i++)
            addToBot(new JainaDiscoveryAction(IHelper.getFewCards(cards, 3, false)));
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = POTION_STRINGS.DESCRIPTIONS[0];
        } else {
            this.description = POTION_STRINGS.DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.makeID("发现"))),
                BaseMod.getKeywordDescription(IHelper.makeID("发现"))
        ));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.makeID("奥术"))),
                BaseMod.getKeywordDescription(IHelper.makeID("奥术"))
        ));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ArcanePotion();
    }
}
