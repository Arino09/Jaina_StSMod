package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class ArcaneMissileToken extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneMissileToken");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ArcaneMissileToken() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, CardColor.COLORLESS,
                CardRarity.SPECIAL, CardTarget.ENEMY, JainaEnums.CardTags.ARCANE);
        setDamage(3);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        this.name = CARD_STRINGS.UPGRADE_DESCRIPTION;
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneMissileToken();
    }
}
