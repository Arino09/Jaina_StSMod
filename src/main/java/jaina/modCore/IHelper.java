package jaina.modCore;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import jaina.actions.SpellDamageAction;

import java.util.ArrayList;

import static jaina.modCore.Core.MOD_ID;

public interface IHelper {
    /**
     * @param id 卡牌/遗物/药水id
     * @return 加上"{MOD_ID}:"前缀的ID，也是本地化json中的ID
     */
    static String makeID(String id) {
        return MOD_ID + ":" + id;
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

    static boolean isSpellDamage(DamageInfo.DamageType type) {
        return type.equals(JainaEnums.DamageType.FIRE) || type.equals(JainaEnums.DamageType.FROST) || type.equals(JainaEnums.DamageType.ARCANE);
    }

    /**
     * 获得一定数量随机吉安娜卡牌
     * （非稀有/特殊卡、非角色卡、非治疗卡）
     *
     * @return 卡牌数组
     */
    static ArrayList<AbstractCard> getRandomJainaCards(int amount) {
        ArrayList<AbstractCard> cardRng = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards()) {
            // 随机卡池为非稀有/特殊卡、角色卡、非治疗卡
            if (!c.rarity.equals(AbstractCard.CardRarity.RARE) && !c.rarity.equals(AbstractCard.CardRarity.SPECIAL) &&
                    c.color.equals(JainaEnums.JAINA_COLOR) && !c.hasTag(AbstractCard.CardTags.HEALING))
                cardRng.add(c);
        }
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(cardRng.get(AbstractDungeon.cardRandomRng.random(cardRng.size() - 1)).makeCopy());
        }
        return cards;
    }
}
