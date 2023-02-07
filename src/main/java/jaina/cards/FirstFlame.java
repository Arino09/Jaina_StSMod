package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class FirstFlame extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("FirstFlame");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public FirstFlame() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(8);
        setDamageType(JainaEnums.DamageType.FIRE);
        this.cardsToPreview = new SecondFlame();
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeDescription(CARD_STRINGS);
        SecondFlame flame = new SecondFlame();
        if (upgraded) flame.upgrade();
        cardsToPreview = flame;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new FireballEffect(p.hb_x, p.hb_y, m.hb_x, m.hb_y)));
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        SecondFlame flame = new SecondFlame();
        if (upgraded) flame.upgrade();
        IHelper.getTempCard(flame);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FirstFlame();
    }

}
