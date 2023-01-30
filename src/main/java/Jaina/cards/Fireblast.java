package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fireblast extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("Fireblast");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public Fireblast() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.BASIC, CardTarget.ENEMY);
        this.damage = 6;
        cardsToPreview = new Burn();
    }

    @Override
    public void upp() {
        this.damage = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        getBurn(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fireblast();
    }
}
