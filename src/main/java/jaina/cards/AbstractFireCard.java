package jaina.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.localization.CardStrings;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.List;

public abstract class AbstractFireCard extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Fire");

    /**
     * 构造函数
     *
     * @param ID          完整的卡牌ID
     * @param useTestArt  是否使用测试图片
     * @param cardStrings 卡牌本地化字段
     * @param cost        能量花费（-1为X，-2不显示消耗）
     * @param type        卡牌类型
     * @param color       卡牌颜色
     * @param rarity      卡牌稀有度
     * @param target      卡牌目标
     */
    public AbstractFireCard(String ID, boolean useTestArt, CardStrings cardStrings, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(ID, useTestArt, cardStrings, cost, type, color, rarity, target, JainaEnums.CardTags.FIRE);
    }

    @Override
    public List<String> getCardDescriptors() {
        return ISpellCard.getCardDescriptors(ID);
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        return ISpellCard.getCustomTooltipsTop(ID);
    }

}
