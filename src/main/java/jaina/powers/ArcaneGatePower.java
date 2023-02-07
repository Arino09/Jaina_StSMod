package jaina.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import jaina.modCore.JainaEnums;

public class ArcaneGatePower extends AbstractJainaPower {
    public static final String POWER_ID = IHelper.makeID("ArcaneGatePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final AbstractGameAction.AttackEffect AE = AbstractGameAction.AttackEffect.BLUNT_LIGHT;
    private static final DamageInfo.DamageType TYPE = JainaEnums.DamageType.ARCANE;

    public ArcaneGatePower(AbstractCreature owner, int dmg) {
        super(POWER_ID, true, NAME, PowerType.BUFF);
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
                    addToBot(new DamageAllEnemiesAction(p, this.amount, TYPE, AE));
                } else {
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(p, amount, TYPE), AE));
                }
            } else {
                addToBot(new DamageAction(m, new DamageInfo(p, amount, TYPE), AE));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
