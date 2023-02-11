package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.BurningPower;


public class Meteor extends AbstractFireCard {

    public static final String ID = IHelper.makeID("Meteor");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public Meteor() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY);
        setDamage(20);
        setMagicNumber(5);
    }

    @Override
    public void upp() {
        upgradeDamage(10);
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        dealDamage(m, AbstractGameAction.AttackEffect.NONE);
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            addToBot(new VFXAction(new FireBurstParticleEffect(mon.hb_x, mon.hb_y)));
            givePower(new BurningPower(mon, magicNumber), magicNumber);
        }
        addToBot(new VFXAction(new FireBurstParticleEffect(p.hb_x, p.hb_y)));
        gainPower(new BurningPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Meteor();
    }

}
