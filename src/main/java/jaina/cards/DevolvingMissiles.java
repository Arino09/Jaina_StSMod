package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class DevolvingMissiles extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("DevolvingMissiles");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public DevolvingMissiles() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        setDamage(2);
        setMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            AbstractMonster randM = AbstractDungeon.getRandomMonster();
            dealDamage(randM, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            givePower(new WeakPower(randM, magicNumber, false), magicNumber);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DevolvingMissiles();
    }

}
