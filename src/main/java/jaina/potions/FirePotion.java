package jaina.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.actions.JainaDiscoveryAction;
import jaina.modCore.Core;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class FirePotion extends AbstractPotion {
    public static final String ID = IHelper.makeID("FirePotion");
    public static final Color LIQUID_COLOR = CardHelper.getColor(246, 96, 20);
    public static final Color HYBRID_COLOR = CardHelper.getColor(254, 210, 89);
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);

    public FirePotion() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.COMMON, PotionSize.CARD, PotionEffect.NONE, LIQUID_COLOR, HYBRID_COLOR, null);
        this.labOutlineColor = Core.COLOR;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        for (int i = 0; i < potency; i++)
            addToBot(new JainaDiscoveryAction(JainaEnums.CardTags.FIRE, true, false));
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
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.localizeKeywordID("Discover"))),
                BaseMod.getKeywordDescription(IHelper.localizeKeywordID("Discover"))
        ));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.localizeKeywordID("Fire"))),
                BaseMod.getKeywordDescription(IHelper.localizeKeywordID("Fire"))
        ));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new FirePotion();
    }
}
