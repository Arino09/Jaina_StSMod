package jaina.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.actions.unique.ExhaustCardFromDrawAction;
import jaina.modCore.Core;
import jaina.modCore.IHelper;

public class IllusionPotion extends AbstractPotion {

    public static final String ID = IHelper.makeID("IllusionPotion");
    public static final Color LIQUID_COLOR = CardHelper.getColor(18, 105, 231);
    public static final Color HYBRID_COLOR = CardHelper.getColor(24, 197, 249);
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);
    private static final int POTENCY = 1;

    public IllusionPotion() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.UNCOMMON, PotionSize.EYE, PotionEffect.NONE, HYBRID_COLOR, LIQUID_COLOR, null);
        this.labOutlineColor = Core.COLOR;
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = POTION_STRINGS.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(GameDictionary.EXHAUST.NAMES[0]),
                GameDictionary.keywords.get(GameDictionary.EXHAUST.NAMES[0])
        ));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return POTENCY;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ExhaustCardFromDrawAction(POTENCY));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new IllusionPotion();
    }

}
