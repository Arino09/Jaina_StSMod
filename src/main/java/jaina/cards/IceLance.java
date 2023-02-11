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
import jaina.powers.FrozenPower;

public class IceLance extends AbstractFrostCard {

    public static final String ID = IHelper.makeID("IceLance");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public IceLance() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(7);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 对冻结的敌人造成伤害，或冻结未被冻结的敌人
        if (m.hasPower(FrozenPower.POWER_ID)) {
            dealDamage(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        } else {
            this.exhaust = true;
            this.addToBot(new FrozenEnemyAction(m, p));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceLance();
    }

}
