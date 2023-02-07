package jaina.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.lang.reflect.Field;


public class MirrorEntity extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("MirrorEntity");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public MirrorEntity() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY, JainaEnums.CardTags.ARCANE);
        this.exhaust = true;
        setDamageType(JainaEnums.DamageType.ARCANE);
    }

    @Override
    public void upp() {
        this.exhaust = false;
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getIntentBaseDmg() > 0) {
            AttackEffect ae;
            try {
                Field f = AbstractMonster.class.getDeclaredField("move");
                f.setAccessible(true);
                EnemyMoveInfo move = (EnemyMoveInfo) f.get(m);

                setDamage(move.baseDamage);
                if (move.isMultiDamage) {
                    setMagicNumber(move.multiplier);
                } else {
                    setMagicNumber(1);
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            ae = damage >= 15 ? AttackEffect.BLUNT_HEAVY : AttackEffect.BLUNT_LIGHT;
            for (int i = 0; i < magicNumber; i++) {
                dealDamage(m, ae);
            }
        }
    }

    // 如果可用则显示金色提示框
    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (canUse(AbstractDungeon.player, null)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    // 检查是否可用，这里第二个形参为 null
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        // 依次检查所有敌人的意图，如果有攻击意图则可用
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (mon.getIntentBaseDmg() > 0) {
                return true;
            }
        }
        this.cantUseMessage = IHelper.UI_STRINGS.TEXT[2];
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new MirrorEntity();
    }

}
