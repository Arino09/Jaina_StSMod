package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArcaneExplosion extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneExplosion");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ArcaneExplosion() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        setDamage(5);
        setMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealAoeDamage(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (upgraded) {
            drawCards(magicNumber);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public ArcaneExplosion makeCopy() {
        return new ArcaneExplosion();
    }
}