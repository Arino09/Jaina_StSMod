package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.HotStreakPower;


public class HotStreak extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("HotStreak");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public HotStreak() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE, JainaEnums.CardTags.FIRE);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.exhaust = false;
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new HotStreakPower(p));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HotStreak();
    }

}
