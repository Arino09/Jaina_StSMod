package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.FrozenPower;


public class FrozenTouch extends AbstractFrostCard {

    public static final String ID = IHelper.makeID("FrozenTouch");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    private int exhaustCount;

    public FrozenTouch() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY);
        setDamage(5);
        setMagicNumber(3);
        exhaustCount = magicNumber;
        returnToHand = true;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new ApplyPowerAction(m, p, new FrozenPower(m, 1)));
        if (--exhaustCount <= 0) {
            exhaust = true;
        }
        rawDescription = CARD_STRINGS.DESCRIPTION + String.format(CARD_STRINGS.EXTENDED_DESCRIPTION[0], exhaustCount);
        initializeDescription();
    }

    @Override
    public void triggerOnExhaust() {
        resetDescription(CARD_STRINGS);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FrozenTouch();
    }

}
