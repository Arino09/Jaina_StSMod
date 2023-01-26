package Jaina.cards;


import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pyroblast extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Pyroblast");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public Pyroblast() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY);
        setDamage(30);
        setMagicNumber(3);
    }

    @Override
    public void upp() {
        upgradeDamage(10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        for (int i = 0; i < magicNumber; i++) {
            IHelper.getTempCard(new Burn());
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pyroblast();
    }

}
