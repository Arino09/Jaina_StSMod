package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.JainaDiscoveryAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class PrimordialGlyph extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("PrimordialGlyph");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public PrimordialGlyph() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 发现一张罕见或稀有卡，本回合法力消耗为0
        addToBot(new JainaDiscoveryAction(true, true, false, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrimordialGlyph();
    }

}
