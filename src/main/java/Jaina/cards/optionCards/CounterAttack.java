package Jaina.cards.optionCards;

import Jaina.ModCore.IHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class CounterAttack extends OptionCard {
    private static final String ID = IHelper.makeID("CounterAttack");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public CounterAttack() {
        super(ID, true, CARD_STRINGS, CardType.POWER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        gainPower(new IntangiblePower(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CounterAttack();
    }
}
