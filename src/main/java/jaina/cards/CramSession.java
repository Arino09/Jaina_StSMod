package jaina.cards;

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


public class CramSession extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("CramSession");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public CramSession() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE);
        setMagicNumber(1);
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
            rawDescription = CARD_STRINGS.DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(magicNumber);
        rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new CramSession();
    }

}
