package jaina.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.actions.ApplyBurningAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class FlameLance extends AbstractFireCard {

    public static final String ID = IHelper.makeID("FlameLance");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public FlameLance() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(15);
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageCallbackAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.FIRE, (c) -> {
            // 给予燃烧层数等于被格挡层数
            if (damage - c > 0) addToBot(new ApplyBurningAction(p, m, damage - c));
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlameLance();
    }

}
