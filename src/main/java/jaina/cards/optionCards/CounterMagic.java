package jaina.cards.optionCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import jaina.modCore.IHelper;

public class CounterMagic extends OptionCard {
    private static final String ID = IHelper.makeID("CounterMagic");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public CounterMagic() {
        super(ID, false, CARD_STRINGS, CardType.POWER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        applyPower(new ArtifactPower(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CounterMagic();
    }
}
