package jaina.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.DrawAttackAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class BookOfSpecters extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("BookOfSpecters");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public BookOfSpecters() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        setMagicNumber(5);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber, new DrawAttackAction()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BookOfSpecters();
    }

}
