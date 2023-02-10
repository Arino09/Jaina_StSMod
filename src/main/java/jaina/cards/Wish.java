package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.WishAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class Wish extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Wish");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public Wish() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WishAction(1, upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wish();
    }

}
