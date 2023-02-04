package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class DragonsBreath extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("DragonsBreath");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public DragonsBreath() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setDamage(12);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.player.hand != null) {
            boolean onlyFire = true;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.hasTag(JainaEnums.CardTags.FIRE)) {
                    onlyFire = false;
                    break;
                }
            }
            if (onlyFire || isCostModifiedForTurn) {
                setCostForTurn(0);
            } else {
                setCostForTurn(cost);
                this.isCostModifiedForTurn = false;
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);

    }

    @Override
    public AbstractCard makeCopy() {
        return new DragonsBreath();
    }

}
