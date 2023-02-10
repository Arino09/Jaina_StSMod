package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Pyroblast extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Pyroblast");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 3;

    public Pyroblast() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(35);
        cardsToPreview = new Burn();
    }

    @Override
    public void upp() {
        upgradeDamage(13);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        dealDamage(m, AbstractGameAction.AttackEffect.NONE);
        IHelper.getBurn(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pyroblast();
    }

}
