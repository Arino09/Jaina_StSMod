package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class ArcaneMissiles extends AbstractArcaneCard {
    public static final String ID = IHelper.makeID("ArcaneMissiles");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    private final ArcaneMissileToken token = new ArcaneMissileToken();

    public ArcaneMissiles() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE);
        setMagicNumber(3); //奥术飞弹数量
        cardsToPreview = token; //预览卡牌：奥术弹
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 添加奥术弹到手牌
        for (int i = 0; i < magicNumber; i++) {
            IHelper.getTempCard(token);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneMissiles();
    }
}
