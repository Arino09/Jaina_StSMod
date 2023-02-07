package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.DragonsFuryAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class DragonsFury extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("DragonsFury");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -1;

    public DragonsFury() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(8);
        setDamageType(JainaEnums.DamageType.FIRE);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DragonsFuryAction(p, this.damage, freeToPlayOnce, energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DragonsFury();
    }

}
