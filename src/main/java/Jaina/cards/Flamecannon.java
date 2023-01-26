package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flamecannon extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("Flamecannon");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Flamecannon() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE);
        this.setDamage(13);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randM = AbstractDungeon.getRandomMonster();
        dealDamage(randM, AbstractGameAction.AttackEffect.FIRE);
        IHelper.getTempCard(new Burn());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flamecannon();
    }
}
