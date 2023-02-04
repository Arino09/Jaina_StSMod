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


public class MagicTrick extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("MagicTrick");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public MagicTrick() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //  发现一张耗费为1的非稀有卡
        ArrayList<AbstractCard> cardRng = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards()) {
            boolean condition = c.rarity.equals(CardRarity.COMMON) || c.rarity.equals(CardRarity.UNCOMMON);
            if (upgraded) {
                condition = c.rarity.equals(CardRarity.UNCOMMON) || c.rarity.equals(CardRarity.RARE);
            }
            if (c.color.equals(JainaEnums.JAINA_COLOR) && !c.hasTag(AbstractCard.CardTags.HEALING)
                    && condition && c.cost == 1) {
                cardRng.add(c);
            }
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
