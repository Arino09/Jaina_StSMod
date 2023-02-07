package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.FrozenPower;


public class Icicle extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Icicle");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public Icicle() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, JainaEnums.CardTags.FROST);
        setDamage(4);
        setDamageType(JainaEnums.DamageType.FROST);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(1);
        if (m.hasPower(FrozenPower.POWER_ID)) {
            dealDamage(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Icicle();
    }

}
