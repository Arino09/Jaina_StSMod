package jaina.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.SpellDamagePower;


public class CramSession extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("CramSession");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public CramSession() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        setMagicNumber(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(SpellDamagePower.POWER_ID)) {
            this.magicNumber = baseMagicNumber + AbstractDungeon.player.getPower(SpellDamagePower.POWER_ID).amount;
        }
        drawCards(magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CramSession();
    }

}
