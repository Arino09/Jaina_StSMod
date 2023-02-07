package jaina.cards;

import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.LoseSpellDamagePower;
import jaina.powers.SpellDamagePower;


public class ArcaneBrilliance extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ArcaneBrilliance");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ArcaneBrilliance() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        setMagicNumber(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new SpellDamagePower(p, magicNumber));
        gainPower(new LoseSpellDamagePower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneBrilliance();
    }

}
