package jaina.modCore;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import jaina.actions.SpellDamageAction;
import jaina.cards.ShiftingScroll;

import java.util.ArrayList;

import static jaina.modCore.Core.MOD_ID;

public interface IHelper {
    UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString("jaina:ui");

    /**
     * @param id 卡牌/遗物/药水/能力id
     * @return 加上"{MOD_ID}:"前缀的ID，也是本地化json中的ID
     */
    static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

    /**
     * 在弃牌堆中加入 [灼烧]
     *
     * @param amount [灼烧] 卡牌的数量
     */
    static void getBurn(int amount) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), amount));
    }

    /**
     * 获得临时手牌
     *
     * @param card 抽象卡牌类
     */
    static void getTempCard(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
        AbstractDungeon.actionManager.addToBottom(new SpellDamageAction());
    }

    /**
     * 从卡牌列表中再筛选出一定数量的牌
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
     * 判断卡牌是否受法术伤害影响
     *
     * @param card 卡牌
     * @return 是否受影响
     */
    static boolean isSpellDamage(AbstractCard card) {
        return card.hasTag(JainaEnums.CardTags.FIRE) || card.hasTag(JainaEnums.CardTags.FROST) || card.hasTag(JainaEnums.CardTags.ARCANE);
    }

    /**
     * 生成一定数量随机吉安娜卡牌（非治疗、基础、特殊的职业卡）
     *
     * @param amount      卡牌数量
     * @param hasRare     是否生成稀有卡
     * @param hasUncommon 是否生成罕见卡
     * @param canRepeated 是否允许重复
     * @param hasShift    是否包含变形卷轴
     * @return 卡牌数组
     */
    static ArrayList<AbstractCard> generateRandomJainaCards(int amount,
           boolean hasRare, boolean hasUncommon, boolean hasCommon, boolean canRepeated, boolean hasShift) {
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
        return getFewCards(cardRng, amount, canRepeated);
    }
}
