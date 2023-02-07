package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.ForbiddenFlameAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class ForbiddenFlame extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ForbiddenFlame");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -1;

    public ForbiddenFlame() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(10);
        setDamageType(JainaEnums.DamageType.FIRE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ForbiddenFlameAction(p, m, damage, freeToPlayOnce, energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ForbiddenFlame();
    }

}
