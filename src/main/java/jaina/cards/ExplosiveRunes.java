package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class ExplosiveRunes extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ExplosiveRunes");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ExplosiveRunes() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(8);
        this.setDamageType(JainaEnums.DamageType.FIRE);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower("Minion")) {
            dealDamage(m, 2 * this.damage, AttackEffect.FIRE);
            int overflow = m.currentHealth;
            if (m.isDead) {
                overflow = 2 * this.damage - overflow;
                addToBot(new DamageAllEnemiesAction(p, overflow, damageTypeForTurn, AttackEffect.FIRE));
            }
        } else {
            dealDamage(m, AttackEffect.FIRE);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExplosiveRunes();
    }

}
