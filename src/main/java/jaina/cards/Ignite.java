package jaina.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.BurningPower;
import jaina.powers.FrozenPower;


public class Ignite extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Ignite");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Ignite() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, JainaEnums.CardTags.FIRE);
    }

    @Override
    public void upp() {
        updateCost(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 触发所有敌人身上的燃烧
        for (AbstractMonster mon : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mon.isDeadOrEscaped() && mon.hasPower(BurningPower.POWER_ID)) {
                mon.getPower(BurningPower.POWER_ID).onSpecificTrigger();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ignite();
    }

}
