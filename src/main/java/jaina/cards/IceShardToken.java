package jaina.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.FrozenPower;

import java.util.List;

import static jaina.cards.AbstractJainaCard.getImgPath;


public class IceShardToken extends CustomCard {

    public static final String ID = IHelper.makeID("IceShardToken");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;

    public IceShardToken() {
        super(ID, CARD_STRINGS.NAME, getImgPath(CardType.ATTACK, IceLance.ID), COST,
                CARD_STRINGS.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(JainaEnums.CardTags.FROST);
        this.exhaust = true;
        this.damage = this.baseDamage = 7;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 对冻结的敌人造成伤害，或冻结未被冻结的敌人
        if (m.hasPower(FrozenPower.POWER_ID)) {
            this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else {
            this.addToBot(new ApplyPowerAction(m, p, new FrozenPower(m, 1)));
        }
    }

    @Override
    public List<String> getCardDescriptors() {
        return ISpellCard.getCardDescriptors(AbstractFrostCard.ID);
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceShardToken();
    }

}
