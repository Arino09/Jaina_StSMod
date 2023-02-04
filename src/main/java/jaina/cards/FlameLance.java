package jaina.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.BurningPower;
import jaina.powers.WildfirePower;


public class FlameLance extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("FlameLance");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public FlameLance() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(10);
        setMagicNumber(4);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageCallbackAction(m, new DamageInfo(p, this.damage, JainaEnums.DamageType.FIRE),
                AbstractGameAction.AttackEffect.FIRE, (c) -> {
            if (c > 0) {
                givePower(new BurningPower(m, magicNumber), magicNumber);
            }
        }));
        IHelper.getBurn(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlameLance();
    }

}
