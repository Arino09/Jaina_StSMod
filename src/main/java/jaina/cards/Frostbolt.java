package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.FrozenEnemyAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Frostbolt extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("Frostbolt");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Frostbolt() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY, JainaEnums.CardTags.FROST);
        setDamage(3);
        setDamageType(JainaEnums.DamageType.FROST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new FrozenEnemyAction(m, p));
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
