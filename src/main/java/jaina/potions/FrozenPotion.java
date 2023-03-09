package jaina.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import jaina.modCore.Core;
import jaina.modCore.IHelper;
import jaina.powers.FrozenPower;

public class FrozenPotion extends AbstractPotion {

    public static final String ID = IHelper.makeID("FrozenPotion");
    private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(ID);

    public FrozenPotion() {
        super(POTION_STRINGS.NAME, ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.BLUE);
        this.isThrown = true;
        this.targetRequired = true;
        this.labOutlineColor = Core.COLOR;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new FrozenPower((AbstractMonster) target, 1)));
        if (getPotency() > 1) {
            addToBot(new ApplyPowerAction(AbstractDungeon.getRandomMonster((AbstractMonster) target),
                    AbstractDungeon.player, new FrozenPower((AbstractMonster) target, 1)));
        }
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
                TipHelper.capitalize(BaseMod.getKeywordTitle(IHelper.localizeKeywordID("Frozen"))),
                BaseMod.getKeywordDescription(IHelper.localizeKeywordID("Frozen"))
        ));
    }

    @Override
    public int getPotency(int i) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new FrozenPotion();
    }

}
