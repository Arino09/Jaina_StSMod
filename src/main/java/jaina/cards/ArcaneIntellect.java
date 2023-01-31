package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class ArcaneIntellect extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ArcaneIntellect");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ArcaneIntellect() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.SELF,JainaEnums.CardTags.ARCANE);
        this.setMagicNumber(3);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneIntellect();
    }

}
