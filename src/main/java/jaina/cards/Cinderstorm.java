package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.CinderstormAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Cinderstorm extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Cinderstorm");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Cinderstorm() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.FIRE);
        setMagicNumber(1);
        cardsToPreview = new Burn();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CinderstormAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cinderstorm();
    }

}
