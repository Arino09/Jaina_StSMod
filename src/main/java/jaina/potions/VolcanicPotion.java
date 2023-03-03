package jaina.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.modCore.Core;
import jaina.modCore.IHelper;
import jaina.powers.CombustionPower;

public class VolcanicPotion extends AbstractPotion {

    public static final String ID = IHelper.makeID("VolcanicPotion");
    public static final Color LIQUID_COLOR = CardHelper.getColor(140, 24, 49);
    public static final Color HYBRID_COLOR = CardHelper.getColor(247, 101, 115);
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);
    private static final int POTENCY = 15;

    public VolcanicPotion() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionEffect.NONE, LIQUID_COLOR, HYBRID_COLOR, null);
        this.labOutlineColor = Core.COLOR;
        this.isThrown = true;
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = String.format(POTION_STRINGS.DESCRIPTIONS[0], potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.makeID("燃烧"))),
                BaseMod.getKeywordDescription(IHelper.makeID("燃烧"))
        ));
    }

    @Override
    public void use(AbstractCreature target) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new CombustionPower(m, POTENCY)));
        }
    }

    @Override
    public int getPotency(int i) {
        return POTENCY;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new VolcanicPotion();
    }
}
