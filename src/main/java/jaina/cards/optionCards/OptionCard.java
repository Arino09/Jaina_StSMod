package jaina.cards.optionCards;

import com.megacrit.cardcrawl.localization.CardStrings;
import jaina.cards.AbstractJainaCard;

public abstract class OptionCard extends AbstractJainaCard {

    private static final int COST = -2;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;

    /**
     * 使用本卡画的选项卡牌
     *
     * @param id          完整卡牌ID
     * @param useTestArt  是否使用测试图片
     * @param cardStrings 本地化字段
     * @param type        卡牌类型
     */
    public OptionCard(String id, boolean useTestArt, CardStrings cardStrings, CardType type) {
        super(id, cardStrings, useTestArt ? getTestImgPath(type) : getOptImgPath(id), COST, type, COLOR, RARITY, TARGET);
    }

    /**
     * 获取选项卡牌路径
     *
     * @param id 完整卡牌ID
     * @return 卡画路径
     */
    public static String getOptImgPath(String id) {
        return "jaina/img/cards/option/" + id.substring(6) + ".png";
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
            initializeDescription();
        }
    }
}
