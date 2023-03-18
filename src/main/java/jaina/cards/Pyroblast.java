package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import jaina.actions.unique.ApplyBurningAction;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Pyroblast extends AbstractFireCard {

    public static final String ID = IHelper.makeID("Pyroblast");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public Pyroblast() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY);
        setDamage(24);
        setMagicNumber(8);
    }

    @Override
    public void upp() {
        upgradeDamage(6);
        upgradeMagicNumber(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        dealDamage(m, AbstractGameAction.AttackEffect.NONE);
        addToBot(new ApplyBurningAction(p, m, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pyroblast();
    }

}
