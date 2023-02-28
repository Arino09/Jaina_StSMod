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
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.modCore.Core;
import jaina.modCore.IHelper;
import jaina.powers.LoseSpellDamagePower;
import jaina.powers.SpellDamagePower;

public class SpellInspirationPotion extends AbstractPotion {

    public static final String ID = IHelper.makeID("SpellInspirationPotion");
    public static final Color LIQUID_COLOR = CardHelper.getColor(99, 33, 183);
    public static final Color HYBRID_COLOR = CardHelper.getColor(221, 103, 237);
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);
    private static final int POTENCY = 5;

    public SpellInspirationPotion() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.COMMON, PotionSize.FAIRY, PotionEffect.NONE, LIQUID_COLOR, HYBRID_COLOR, null);
        this.labOutlineColor = Core.COLOR;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new SpellDamagePower(AbstractDungeon.player, potency)));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new LoseSpellDamagePower(AbstractDungeon.player, potency)));
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = String.format(POTION_STRINGS.DESCRIPTIONS[0], potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.makeID("法术伤害"))),
                BaseMod.getKeywordDescription(IHelper.makeID("法术伤害"))
        ));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return POTENCY;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SpellInspirationPotion();
    }
}
