package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class Flamecannon extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("Flamecannon");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Flamecannon() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.NONE, JainaEnums.CardTags.FIRE);
        setDamage(13);
        cardsToPreview = new Burn();
        setDamageType(JainaEnums.DamageType.FIRE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randM = AbstractDungeon.getRandomMonster();
        addToBot(new VFXAction(new FlameAnimationEffect(randM.hb)));
        dealDamage(randM, AbstractGameAction.AttackEffect.FIRE);
        IHelper.getBurn(1);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flamecannon();
    }
}
