package jaina.powers.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.AbstractJainaPower;

public class ArcaneGatePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("ArcaneGatePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final AbstractGameAction.AttackEffect AE = AbstractGameAction.AttackEffect.BLUNT_LIGHT;

    public ArcaneGatePower(AbstractCreature owner, int dmg) {
        super(POWER_ID, false, NAME, PowerType.BUFF);
        this.owner = owner;
        this.amount = dmg;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;
        if (card.hasTag(JainaEnums.CardTags.ARCANE)) {
            if (m == null) {
                flashWithoutSound();
                if (card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                    addToBot(new DamageAllEnemiesAction(p, this.amount, DamageInfo.DamageType.NORMAL, AE));
                } else {
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(p, amount, DamageInfo.DamageType.NORMAL), AE));
                }
            } else {
                addToBot(new DamageAction(m, new DamageInfo(p, amount, DamageInfo.DamageType.NORMAL), AE));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

}
