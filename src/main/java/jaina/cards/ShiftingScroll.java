package jaina.cards;

import com.badlogic.gdx.graphics.Color;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class ShiftingScroll extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ShiftingScroll");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -2;
    private AbstractJainaCard shiftCard;
    public ShiftingScroll() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        this.tags.add(JainaEnums.CardTags.SHIFT);
        this.selfRetain = true;
        setDamageType(JainaEnums.DamageType.ARCANE);
    }

    @Override
    public void upp() {
        if (shiftCard != null) {
            shiftCard.upp();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = Color.PURPLE.cpy();
    }

    @Override
    public void atTurnStart() {
        shiftCard = (AbstractJainaCard) IHelper.generateRandomJainaCards(1, true, true, true).get(0);
        this.type = shiftCard.type;
        this.name = CARD_STRINGS.UPGRADE_DESCRIPTION + shiftCard.name;
        this.rawDescription = shiftCard.rawDescription;
        this.cost = this.costForTurn = shiftCard.cost;
        this.portrait = shiftCard.portrait;
        this.rarity = shiftCard.rarity;
        this.target = shiftCard.target;
        this.tags = shiftCard.tags;
        this.tags.add(JainaEnums.CardTags.SHIFT);

        this.exhaust = shiftCard.exhaust;
        this.isEthereal = shiftCard.isEthereal;
        this.cardsToPreview = shiftCard.cardsToPreview;

        setDamage(shiftCard.baseDamage);
        setBlock(shiftCard.baseBlock);
        setMagicNumber(shiftCard.baseMagicNumber);
        setDamageType(shiftCard.damageTypeForTurn);

        this.initializeTitle();
        this.initializeDescription();
        this.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        shiftCard.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShiftingScroll();
    }

}
