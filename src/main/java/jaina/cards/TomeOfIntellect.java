package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellDamagePower;


public class TomeOfIntellect extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("TomeOfIntellect");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public TomeOfIntellect() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        this.exhaust = true;
        setMagicNumber(5);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = p.drawPile.size() / magicNumber;
        gainPower(new SpellDamagePower(p, amount));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TomeOfIntellect();
    }

}
