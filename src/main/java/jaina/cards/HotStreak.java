package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.ApplyBurningAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class HotStreak extends AbstractFireCard {

    public static final String ID = IHelper.makeID("HotStreak");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    private boolean lastIsFire = false;

    public HotStreak() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(4);
        setMagicNumber(4);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new ApplyBurningAction(p, m, magicNumber));
        if (lastIsFire) addToBot(new GainEnergyAction(1));
    }

    // 如果可以出发额外效果则显示金色框
    @Override
    public void triggerOnGlowCheck() {
        ArrayList<AbstractCard> a = AbstractDungeon.actionManager.cardsPlayedThisTurn;
        if (a.isEmpty() || !a.get(a.size() - 1).hasTag(JainaEnums.CardTags.FIRE)) {
            lastIsFire = false;
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } else {
            lastIsFire = true;
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HotStreak();
    }

}
