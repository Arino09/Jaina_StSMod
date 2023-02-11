package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class FrostArmor extends AbstractFrostCard {

    public static final String ID = IHelper.makeID("FrostArmor");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public FrostArmor() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        setMagicNumber(4);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new PlatedArmorPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FrostArmor();
    }

}
