package Jaina.ModCore;

import Jaina.cards.AbstractJainaCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TempCard extends AbstractJainaCard {

    public TempCard(String id, CardStrings strings, CardType type, CardColor color) {
        super(id, false, strings, 0, type, color, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy() {
        return null;
    }
}
