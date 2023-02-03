package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class CabalistsTome extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("CabalistsTome");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public CabalistsTome() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        updateCost(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = IHelper.getRandomJainaCards(3);
        // 从随机卡池中选3张卡
        for (AbstractCard c : cards) {
            c.setCostForTurn(0);
            IHelper.getTempCard(c);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CabalistsTome();
    }

}
