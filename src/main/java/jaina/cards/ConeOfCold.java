package jaina.cards;

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
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

public class ConeOfCold extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("ConeOfCold");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public ConeOfCold() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ALL_ENEMY, JainaEnums.CardTags.FROST);
        setDamage(4);
        setBlock(5);
        setDamageType(JainaEnums.DamageType.FROST);
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
