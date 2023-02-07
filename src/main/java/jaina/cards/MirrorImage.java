package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class MirrorImage extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("MirrorImage");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public MirrorImage() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE, JainaEnums.CardTags.ARCANE);
        setBlock(4);
        selfRetain = true;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }

    @Override
    public AbstractCard makeCopy() {
        return new MirrorImage();
    }

}
