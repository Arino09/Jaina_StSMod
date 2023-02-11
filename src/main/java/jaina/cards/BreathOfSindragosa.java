package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.FrozenEnemyAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class BreathOfSindragosa extends AbstractFrostCard {

    public static final String ID = IHelper.makeID("BreathOfSindragosa");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public BreathOfSindragosa() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE);
        setDamage(4);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randM = AbstractDungeon.getRandomMonster();
        dealDamage(randM, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new FrozenEnemyAction(randM, p));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BreathOfSindragosa();
    }

}
