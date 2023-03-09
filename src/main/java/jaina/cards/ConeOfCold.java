package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.FrozenPower;

public class ConeOfCold extends AbstractFrostCard {

    public static final String ID = IHelper.makeID("ConeOfCold");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ConeOfCold() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        setDamage(3);
        setBlock(2);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeBlock(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster am : AbstractDungeon.getMonsters().monsters) {
            // 每当对被冻结敌人造成伤害时获得格挡
            dealDamage(am, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            if (am.hasPower(FrozenPower.POWER_ID)) gainBlock();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ConeOfCold();
    }

}
