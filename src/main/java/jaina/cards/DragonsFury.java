package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.unique.DragonsFuryAction;
import jaina.modCore.Core;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DragonsFury extends AbstractFireCard {

    public static final String ID = IHelper.makeID("DragonsFury");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final Logger logger = LogManager.getLogger(Core.class.getName());

    private static final int COST = -1;

    public DragonsFury() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setDamage(4);
        setMagicNumber(4);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DragonsFuryAction(p, this.damage, this.magicNumber, freeToPlayOnce, energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() {
        DragonsFury card = new DragonsFury();
        card.energyOnUse = this.energyOnUse;
        return card;
    }

}
