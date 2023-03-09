package jaina.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class Wish extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("Wish");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 4;

    public Wish() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeBaseCost(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(JainaEnums.JAINA_LIBRARY)) {
            if (c.color == JainaEnums.JAINA_COLOR) {
                cards.add(c);
            }
        }
        addToBot(new SelectCardsAction(cards, IHelper.UI_STRINGS.TEXT[3], (c) -> {
            AbstractCard tmp = c.get(0);
            tmp.setCostForTurn(0);
            tmp.isCostModifiedForTurn = true;
            IHelper.getTempCard(tmp);
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wish();
    }

}
