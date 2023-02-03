package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.JainaDiscoveryAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class FontOfPower extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("FontOfPower");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public FontOfPower() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
        updateCost(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = IHelper.generateRandomJainaCards(3, true, true);
        // 如果这是手牌的最后一张则保留全部三张牌
        if (p.hand.group.size() == 1) {
            for (AbstractCard c : cards) {
                IHelper.getTempCard(c);
            }
        } else {
            addToBot(new JainaDiscoveryAction(cards));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FontOfPower();
    }

}
