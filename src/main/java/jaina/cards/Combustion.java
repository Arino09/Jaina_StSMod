package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.CombustionPower;


public class Combustion extends AbstractFireCard {

    public static final String ID = IHelper.makeID("Combustion");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Combustion() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(8);
        setMagicNumber(4);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        dealDamage(monster, AbstractGameAction.AttackEffect.FIRE);
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.equals(monster)) {
                givePower(new CombustionPower(m, magicNumber), magicNumber);
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Combustion();
    }

}
