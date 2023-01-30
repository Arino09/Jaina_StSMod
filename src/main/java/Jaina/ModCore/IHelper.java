package Jaina.ModCore;

import Jaina.cards.AbstractJainaCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Jaina.ModCore.Core.MOD_ID;

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
    }

    /**
     * 增加法术伤害值
     *
     * @param card 作用的卡牌
     * @param amount 法术伤害层数
     */
    static void spellDamageApply (AbstractJainaCard card, int amount) {
        if (card.hasTag(JainaEnums.ARCANE) || card.hasTag(JainaEnums.FIRE) || card.hasTag(JainaEnums.FROST)) {
            card.spellDamage += amount;
        }
    }
}
