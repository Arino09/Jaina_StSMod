package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class ShootingStar extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ShootingStar");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ShootingStar() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        setDamage(2);
        setMagicNumber(4);
        setDamageType(JainaEnums.DamageType.ARCANE);
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dealRandDamage(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new ShootingStar();
    }

}
