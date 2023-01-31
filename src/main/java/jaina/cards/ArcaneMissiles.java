package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class ArcaneMissiles extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneMissiles");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ArcaneMissiles() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.SELF,JainaEnums.CardTags.ARCANE);
        setMagicNumber(4); //奥术飞弹数量
        cardsToPreview = new ArcaneMissileToken(); //预览卡牌：奥术弹
        this.setDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArcaneMissileToken token = new ArcaneMissileToken();
        // 奥术飞弹+给的是奥术弹+
        if (upgraded) token.upgrade();
        // 添加奥术弹到手牌
        for (int i = 0; i < magicNumber; i++) {
            IHelper.getTempCard(new ArcaneMissileToken());
        }
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
        this.cardsToPreview.upgrade();
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneMissiles();
    }
}
