package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import Jaina.actions.FrozenEnemyAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Frostbolt extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("Frostbolt");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Frostbolt() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        // 没有冻结的情况下给予一层冻结
        if (!m.hasPower(IHelper.makeID("FrozenPower"))) {
            this.addToBot(new FrozenEnemyAction(m, p));
        }
    }

    @Override
    public void upp() {
        this.upgradeDamage(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Frostbolt();
    }
}
