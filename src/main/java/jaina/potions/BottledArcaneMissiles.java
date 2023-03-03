package jaina.potions;

import basemod.helpers.CardPowerTip;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.cards.ArcaneMissileToken;
import jaina.modCore.Core;
import jaina.modCore.IHelper;

public class BottledArcaneMissiles extends AbstractPotion {
    public static final String ID = IHelper.makeID("BottledArcaneMissiles");
    public static final Color LIQUID_COLOR = CardHelper.getColor(160, 84, 221);
    public static final Color HYBRID_COLOR = CardHelper.getColor(253, 241, 253);
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);
    private static final int POTENCY = 3;

    public BottledArcaneMissiles() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.UNCOMMON, PotionSize.JAR, PotionEffect.NONE, LIQUID_COLOR, HYBRID_COLOR, null);
        this.labOutlineColor = Core.COLOR;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        ArcaneMissileToken token = new ArcaneMissileToken();
        token.upgrade();
        for (int i = 0; i < potency; i++) {
            IHelper.getTempCard(token);
        }
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = String.format(POTION_STRINGS.DESCRIPTIONS[0], potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        ArcaneMissileToken token = new ArcaneMissileToken();
        token.upgrade();
        this.tips.add(new CardPowerTip(token));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return POTENCY;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BottledArcaneMissiles();
    }
}
