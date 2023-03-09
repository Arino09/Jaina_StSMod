package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellForcePower;
import jaina.powers.unique.LoseSpellForcePower;


public class ArcaneBrilliance extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ArcaneBrilliance");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ArcaneBrilliance() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        setMagicNumber(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new SpellForcePower(p, magicNumber));
        gainPower(new LoseSpellForcePower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneBrilliance();
    }

}
