package jaina.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import jaina.modCore.Core;
import jaina.modCore.IHelper;

public class ManaBiscuit extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ManaBiscuit");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ManaBiscuit() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, CardColor.COLORLESS,
                CardRarity.SPECIAL, CardTarget.NONE);
        setMagicNumber(2);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!Settings.DISABLE_EFFECTS) {
            addToBot(new VFXAction(new BorderFlashEffect(Core.COLOR, true)));
        }
        addToBot(new VFXAction(new MiracleEffect()));
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ManaBiscuit();
    }

}
