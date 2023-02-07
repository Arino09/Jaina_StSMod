package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.unique.FlameWardPower;


public class FlameWard extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("FlameWard");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public FlameWard() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, JainaEnums.CardTags.FIRE);
        setBlock(10);
        setMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeBlock(4);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        gainPower(new FlameWardPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlameWard();
    }

}
