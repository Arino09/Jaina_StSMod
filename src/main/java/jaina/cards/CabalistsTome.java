package jaina.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
        ArrayList<AbstractCard> cardRng = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards()) {
            // 随机卡池为非稀有卡、角色卡、非治疗卡
            if (!c.rarity.equals(CardRarity.RARE) && c.color.equals(JainaEnums.JAINA_COLOR) && !c.hasTag(AbstractCard.CardTags.HEALING))
                cardRng.add(c);
        }
        // 从随机卡池中选3张卡
        for (int i = 0; i < 3; i++) {
            AbstractCard card = cardRng.get(AbstractDungeon.cardRandomRng.random(cardRng.size() - 1)).makeCopy();
            card.setCostForTurn(0);
            IHelper.getTempCard(card);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CabalistsTome();
    }

}
