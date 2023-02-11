package jaina.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellDamagePower;

import java.util.ArrayList;
import java.util.List;


public class FontOfPower extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("FontOfPower");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public FontOfPower() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE);
        setMagicNumber(2);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.exhaust = false;
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void applyPowers() {
        AbstractPower power = AbstractDungeon.player.getPower(SpellDamagePower.POWER_ID);
        if (AbstractDungeon.player.hasPower(SpellDamagePower.POWER_ID)) {
            // 仅当法伤数值变化时才更新描述
            if (power.amount + baseMagicNumber > magicNumber) {
                magicNumber = power.amount + baseMagicNumber;
                rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0] + magicNumber + CARD_STRINGS.EXTENDED_DESCRIPTION[1];
            }
        } else {
            resetDescription();
        }
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        resetDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        resetDescription();
    }

    private void resetDescription() {
        rawDescription = CARD_STRINGS.DESCRIPTION;
        if (upgraded) {
            rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new FontOfPower();
    }

}
