package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.FrozenCloneAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class FrozenClone extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("FrozenClone");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -1;

    public FrozenClone() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, JainaEnums.CardTags.FROST);
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
    }

    // 可用时才会调用这个方法
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getIntentBaseDmg() >= 0) {
            // 如果意图为攻击则获得与攻击力相同的格挡值
            setBlock(m.getIntentDmg());
            addToBot(new FrozenCloneAction(p, block, freeToPlayOnce, energyOnUse, upgraded));
        }
    }

    // 如果可用则显示金色提示框
    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (canUse(AbstractDungeon.player, null)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    // 检查是否可用，这里第二个形参为 null
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        // 依次检查所有敌人的意图，如果有攻击意图则可用
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (mon.getIntentBaseDmg() > 0) {
                return true;
            }
        }
        // 如果无攻击意图则设定不能使用的文本
        this.cantUseMessage = IHelper.UI_STRINGS.TEXT[2];
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new FrozenClone();
    }

}
