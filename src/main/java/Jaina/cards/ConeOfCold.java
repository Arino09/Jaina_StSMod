package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

public class ConeOfCold extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ConeOfCold");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ConeOfCold() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setDamage(3);
        setBlock(5);

    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(3, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(3, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }
        dealAoeDamage(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        gainBlock();
    }

    @Override
    public AbstractCard makeCopy() {
        return new ConeOfCold();
    }

}
