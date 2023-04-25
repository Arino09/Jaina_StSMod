package jaina.cards;

import com.megacrit.cardcrawl.localization.CardStrings;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.List;

public abstract class AbstractFrostCard extends AbstractJainaCard {

    private static final String SPELL_TYPE = IHelper.makeID("Frost");

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
    public AbstractFrostCard(String ID, boolean useTestArt, CardStrings cardStrings, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(ID, useTestArt, cardStrings, cost, type, color, rarity, target, JainaEnums.CardTags.FROST);
    }

    @Override
    public List<String> getCardDescriptors() {
        return getCardDescriptors(SPELL_TYPE);
    }

}
