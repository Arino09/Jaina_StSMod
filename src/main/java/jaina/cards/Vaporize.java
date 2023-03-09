package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.unique.VaporizePower;

public class Vaporize extends AbstractFireCard {

    public static final String ID = IHelper.makeID("Vaporize");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Vaporize() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(4);
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
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
