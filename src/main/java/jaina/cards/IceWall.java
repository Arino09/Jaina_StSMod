package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class IceWall extends AbstractFrostCard {

    public static final String ID = IHelper.makeID("IceWall");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public IceWall() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(5);
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.currentHealth > 0) amount++;
        }
        for (int i = 0; i < amount; i++) {
            gainBlock(block);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceWall();
    }

}
