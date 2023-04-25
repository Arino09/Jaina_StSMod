package jaina.modCore;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import jaina.actions.SpellForceAction;
import jaina.cards.ShiftingScroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static jaina.modCore.Core.MOD_ID;

public interface IHelper {

    UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(IHelper.makeID("ui"));

    /**
     * 返回唯一id
     *
     * @param id 卡牌/遗物/药水/能力id
     * @return 加上"${MOD_ID}:"前缀的ID，也是本地化json中的ID
     */
    static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

    /**
     * 本地化英文关键词并转换成ID
     *
     * @param engKeyword 英文关键词
     * @return 当前语言的关键词ID
     */
    static String localizeKeywordID(String engKeyword) {
        // 在ui.json中保存有关键词映射表
        Map<String, String> keywords = CardCrawlGame.languagePack.getUIString(IHelper.makeID("keywords")).TEXT_DICT;
        return IHelper.makeID(keywords.get(engKeyword));
    }

    /**
     * 判断卡牌是否受法术强度影响
     *
     * @param card 卡牌
     * @return 是否受影响
     */
    static boolean isSpell(AbstractCard card) {
        return card.hasTag(JainaEnums.CardTags.FIRE) || card.hasTag(JainaEnums.CardTags.FROST) || card.hasTag(JainaEnums.CardTags.ARCANE);
    }

    /**
     * 获得临时手牌
     *
     * @param card 抽象卡牌
     */
    static void getTempCard(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
        AbstractDungeon.actionManager.addToBottom(new SpellForceAction());
    }

    /**
     * 从给定卡池中再筛选出一定数量的牌
     *
     * @param group       筛选池
     * @param amount      数量
     * @param canRepeated 是否允许重复
     * @return 卡牌数组
     */
    static ArrayList<AbstractCard> getFewCards(ArrayList<AbstractCard> group, int amount, boolean canRepeated) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        while (cards.size() < amount) {
            AbstractCard card = group.get(AbstractDungeon.cardRandomRng.random(group.size() - 1)).makeCopy();

            //  如果不能重复，则去除重复的卡牌
            if (!canRepeated) {
                boolean isRepeated = false;
                for (AbstractCard c : cards) {
                    if (card.cardID.equals(c.cardID)) {
                        isRepeated = true;
                        break;
                    }
                }
                if (isRepeated) continue;
            }
            if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                card.upgrade();
            }
            cards.add(card);
        }
        return cards;
    }

    /**
     * 生成随机吉安娜卡牌（非治疗、基础、特殊的职业卡）
     *
     * @param hasRare     是否生成稀有卡
     * @param hasUncommon 是否生成罕见卡
     * @param hasCommon   是否生成普通卡
     * @param hasShift    是否包含“变形卷轴”
     * @return 生成的卡牌列表
     */
    static ArrayList<AbstractCard> generateRandomJainaCards(boolean hasRare, boolean hasUncommon, boolean hasCommon, boolean hasShift) {
        ArrayList<AbstractCard> cardRng = new ArrayList<>();

        for (AbstractCard c : CardLibrary.getCardList(JainaEnums.JAINA_LIBRARY)) {
            // 初始条件为非治疗、基础、特殊的职业卡
            boolean conditions = !c.rarity.equals(AbstractCard.CardRarity.SPECIAL) && !c.rarity.equals(AbstractCard.CardRarity.BASIC)
                    && !c.hasTag(AbstractCard.CardTags.HEALING);
            if (!hasRare)
                conditions = conditions && !c.rarity.equals(AbstractCard.CardRarity.RARE);
            if (!hasShift)
                conditions = conditions && !c.cardID.equals(ShiftingScroll.ID);
            if (!hasUncommon)
                conditions = conditions && !c.rarity.equals(AbstractCard.CardRarity.UNCOMMON);
            if (!hasCommon)
                conditions = conditions && !c.rarity.equals(AbstractCard.CardRarity.COMMON);
            if (conditions)
                cardRng.add(c);
        }
        return cardRng;
    }

    /**
     * 生成一种类型的法术
     *
     * @param tags 法术类型
     * @return 法术卡牌数组
     */
    static ArrayList<AbstractCard> generateTypeOfSpell(AbstractCard.CardTags tags) {
        ArrayList<AbstractCard> cardRng = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getCardList(JainaEnums.JAINA_LIBRARY)) {
            if (c.hasTag(tags) && !c.rarity.equals(AbstractCard.CardRarity.BASIC)) {
                cardRng.add(c);
            }
        }
        return cardRng;
    }
}
