package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Fireball extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Fireball");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;

    public Fireball() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(10);
        this.cardsToPreview = new Burn();
        setDamageType(JainaEnums.DamageType.FIRE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new FireballEffect(p.hb_x, p.hb_y, m.hb_x, m.hb_y)));
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        IHelper.getBurn(1);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fireball();
    }
}
