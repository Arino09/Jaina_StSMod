package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.ApplyBurningAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Flamestrike extends AbstractFireCard {
    public static final String ID = IHelper.makeID("Flamestrike");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Flamestrike() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        setDamage(6);
        setMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster mon) {
        dealAoeDamage(AbstractGameAction.AttackEffect.FIRE);
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyBurningAction(p, m, magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flamestrike();
    }
}
