package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.HotStreakAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.HotStreakPower;


public class HotStreak extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("HotStreak");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public HotStreak() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE, JainaEnums.CardTags.FIRE);
        setMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HotStreakAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HotStreak();
    }

}
