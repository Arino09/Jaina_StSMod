package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.JainaDiscoveryAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class MagicTrick extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("MagicTrick");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public MagicTrick() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardRng = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(JainaEnums.JAINA_LIBRARY)) {
            //  发现一张耗费为1的非稀有卡
            boolean condition = c.rarity.equals(CardRarity.COMMON) || c.rarity.equals(CardRarity.UNCOMMON);
            //  发现一张耗费为1的非普通卡
            if (upgraded) condition = c.rarity.equals(CardRarity.UNCOMMON) || c.rarity.equals(CardRarity.RARE);
            if (!c.hasTag(AbstractCard.CardTags.HEALING) && condition && c.cost == 1)
                cardRng.add(c);
        }
        ArrayList<AbstractCard> cards = IHelper.getFewCards(cardRng, 3, false);
        for (AbstractCard c : cards) {
            c.setCostForTurn(0);
            c.freeToPlayOnce = true;
        }
        addToBot(new JainaDiscoveryAction(cards));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicTrick();
    }

}
