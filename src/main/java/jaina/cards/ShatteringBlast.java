package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.FrozenPower;


public class ShatteringBlast extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ShatteringBlast");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ShatteringBlast() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, JainaEnums.CardTags.FROST);
        setDamage(8);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (mon.hasPower(FrozenPower.POWER_ID)) dealDamage(mon, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShatteringBlast();
    }

}
