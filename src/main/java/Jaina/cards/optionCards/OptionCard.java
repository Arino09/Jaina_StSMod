package Jaina.cards.optionCards;

import Jaina.cards.AbstractJainaCard;
import Jaina.cards.Counterspell;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CardStrings;

public abstract class OptionCard extends AbstractJainaCard {

    private static final int COST = -2;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    /**
     * 使用其他卡画的选项卡牌
     * @param id 完整卡牌ID
     * @param card 使用该牌的卡画
     * @param cardStrings 本地化字段
     * @param type 卡牌类型
     */
    public OptionCard(String id, AbstractJainaCard card, CardStrings cardStrings, CardType type) {
        super(id, cardStrings, getImgPath(card.type, card.cardID), COST, type, COLOR, RARITY, TARGET);
    }
    /**
     * 使用本卡画的选项卡牌
     * @param id 完整卡牌ID
     * @param cardStrings 本地化字段
     * @param type 卡牌类型
     */
    public OptionCard(String id, CardStrings cardStrings, CardType type) {
        super(id, cardStrings, getOptImgPath(id), COST, type, COLOR, RARITY, TARGET);
    }

    /**
     * 获取选项卡牌路径
     * @param id 完整卡牌ID
     * @return 卡画路径
     */
    public static String getOptImgPath(String id) {
        return "Jaina/img/cards/option/" + id.substring(6) + ".png";
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
            initializeDescription();
        }
    }

    public void upp() {

    }
}
