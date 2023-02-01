package jaina.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class IceShield extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("IceShield");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public IceShield() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.NONE, JainaEnums.CardTags.FROST);
        setBlock(5);
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = AbstractDungeon.getMonsters().monsters.size();
        for (int i = 0; i < amount; i++) {
            gainBlock(block);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceShield();
    }

}
