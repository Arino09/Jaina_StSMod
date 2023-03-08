package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.FrozenPower;

public class Frostbolt extends AbstractFrostCard {
    public static final String ID = IHelper.makeID("Frostbolt");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Frostbolt() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY);
        setDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new ApplyPowerAction(m, p, new FrozenPower(m, 1)));
    }

    @Override
    public void upp() {
        this.upgradeDamage(2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Frostbolt();
    }
}
