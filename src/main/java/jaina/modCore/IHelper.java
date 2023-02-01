package jaina.modCore;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.actions.SpellDamageAction;

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

}
