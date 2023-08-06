package jaina.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;


public class ShiftingScroll extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ShiftingScroll");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -2;
    private AbstractJainaCard shiftCard;

    public ShiftingScroll() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE);
        this.selfRetain = true;
    }

    @Override
    public void upp() {
        // 战斗中升级本体
        if (shiftCard != null) {
            shiftCard.upp();
            this.upgraded = false;
        } else {
            // 战斗外升级加固有
            this.isInnate = true;
            upgradeDescription(CARD_STRINGS);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = Color.PURPLE.cpy();
    }

    @Override
    public void atTurnStart() {

        ArrayList<AbstractCard> cardRng = IHelper.generateRandomJainaCards(true, true, true, false);
        shiftCard = (AbstractJainaCard) IHelper.getFewCards(cardRng, 1, false).get(0).makeCopy();

        if (shiftCard.cost == -1) this.energyOnUse = shiftCard.energyOnUse;
        if (upgraded) shiftCard.upp();
        if (!shiftCard.selfRetain) {
            this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[1] + shiftCard.rawDescription;
        } else {
            this.rawDescription = shiftCard.rawDescription;
        }

        this.type = shiftCard.type;
        this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[0] + shiftCard.name;
        this.cost = this.costForTurn = shiftCard.cost;
        this.portrait = shiftCard.portrait;
        this.rarity = shiftCard.rarity;
        this.target = shiftCard.target;
        this.tags = shiftCard.tags;
        this.exhaust = shiftCard.exhaust;
        this.isEthereal = shiftCard.isEthereal;
        this.cardsToPreview = shiftCard.cardsToPreview;

        setDamage(shiftCard.baseDamage);
        setBlock(shiftCard.baseBlock);
        setMagicNumber(shiftCard.baseMagicNumber);

        this.initializeTitle();
        this.initializeDescription();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if (shiftCard != null) {
            return true;
        }
        this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[2];
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        shiftCard.energyOnUse = this.energyOnUse;
        shiftCard.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShiftingScroll();
    }

}