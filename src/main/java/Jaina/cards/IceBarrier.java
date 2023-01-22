package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import Jaina.powers.IceBarrierPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceBarrier extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("IceBarrier");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public IceBarrier() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(12);
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            givePower(new IceBarrierPower(p, false), 1);
        } else {
            givePower(new IceBarrierPower(p, true), 1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceBarrier();
    }

}
