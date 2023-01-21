package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArcaneMissiles extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneMissiles");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ArcaneMissiles() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        setMagicNumber(3); //奥术飞弹数量
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArcaneMissileToken token = new ArcaneMissileToken();
        if(upgraded) token.upgrade();
        for(int i = 0; i < magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(token, true));
        }
    }
    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDescription(CARD_STRINGS);
    }
    @Override
    public AbstractCard makeCopy() {
        return new ArcaneMissiles();
    }
}
