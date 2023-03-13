package jaina.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.cards.optionCards.CounterAttack;
import jaina.cards.optionCards.CounterDamage;
import jaina.cards.optionCards.CounterMagic;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class Counterspell extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("Counterspell");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public Counterspell() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        // 添加三张选项卡牌
        choices.add(new CounterAttack());
        choices.add(new CounterDamage());
        choices.add(new CounterMagic());
        // 释放选择的效果
        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Counterspell();
    }
}
