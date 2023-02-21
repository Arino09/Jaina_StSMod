package jaina.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import jaina.modCore.IHelper;

public class ScrollOfWonder extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ScrollOfWonder");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -2;

    public ScrollOfWonder() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, CardColor.COLORLESS,
                CardRarity.SPECIAL, CardTarget.NONE);
        this.purgeOnUse = true;
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new AutoplayCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 随机生成一张吉安娜卡牌
        AbstractCard card = IHelper.generateRandomJainaCards(1, true, true, true, false, false).get(0);
        // 免费释放卡牌且不留痕迹
        card.purgeOnUse = true;
        card.ignoreEnergyOnUse = true;
        addToBot(new VFXAction(new ShowCardBrieflyEffect(card, 300, 300)));
        card.use(p, AbstractDungeon.getRandomMonster());
        drawCards(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrollOfWonder();
    }

}
