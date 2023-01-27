package Jaina.cards;

import Jaina.ModCore.Core;
import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
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

public class ManaBiscuit extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ManaBiscuit");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public ManaBiscuit() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.selfRetain = true;
        setMagicNumber(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!Settings.DISABLE_EFFECTS) {
            addToBot(new VFXAction(new BorderFlashEffect(Core.JAINA_COLOR, true)));
        }
        addToBot(new VFXAction(new MiracleEffect()));
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ManaBiscuit();
    }

}
