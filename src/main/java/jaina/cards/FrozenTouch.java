package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.FrozenEnemyAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class FrozenTouch extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("FrozenTouch");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    private int exhaustCount;

    public FrozenTouch() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY, JainaEnums.CardTags.FROST);
        setDamage(3);
        setMagicNumber(3);
        exhaustCount = magicNumber;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new FrozenEnemyAction(m, p));
        if (--exhaustCount <= 0) {
            exhaust = true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FrozenTouch();
    }

}
