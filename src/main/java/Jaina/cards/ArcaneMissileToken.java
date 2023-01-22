package Jaina.cards;

import Jaina.ModCore.IHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArcaneMissileToken extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneMissileToken");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ArcaneMissileToken() {
        super(ID, true, CARD_STRINGS, COST, CardType.ATTACK, CardColor.COLORLESS,
                CardRarity.COMMON, CardTarget.ENEMY);
        // 造成3点伤害并消耗
        setDamage(3);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneMissileToken();
    }
}
