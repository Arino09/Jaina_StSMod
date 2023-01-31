package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Defend extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Defend");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    //卡牌基本信息
    private static final int COST = 1;

    public Defend() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.BASIC, CardTarget.SELF, CardTags.STARTER_DEFEND);
        setBlock(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend();
    }

}
