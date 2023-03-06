package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellForcePower;


public class TomeOfIntellect extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("TomeOfIntellect");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public TomeOfIntellect() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        setMagicNumber(5);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = p.drawPile.size() / magicNumber;
        if (amount > 0) {
            gainPower(new SpellForcePower(p, amount));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TomeOfIntellect();
    }

}
