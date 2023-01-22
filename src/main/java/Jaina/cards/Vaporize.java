package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import Jaina.powers.VaporizePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Vaporize extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Vaporize");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Vaporize() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(6);
        setDamage(6);
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!upgraded) {
            gainPower(new VaporizePower(p, false));
        } else {
            gainPower(new VaporizePower(p, true));
        }
        gainBlock();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Vaporize();
    }

}
