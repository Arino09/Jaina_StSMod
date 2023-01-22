package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class Counterspell extends AbstractJainaCard implements ModalChoice.Callback {

    public static final String ID = IHelper.makeID("Counterspell");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;
    private ModalChoice modal;

    public Counterspell() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        modal = new ModalChoiceBuilder()
                .setCallback(this)
                .setColor(JainaEnums.JAINA_COLOR)
                // 3个选项的本地化文本存放在 EXTENDED_DESCRIPTION 的键值中
                .addOption(CARD_STRINGS.EXTENDED_DESCRIPTION[0], CardTarget.NONE)
                .setColor(JainaEnums.JAINA_COLOR)
                .addOption(CARD_STRINGS.EXTENDED_DESCRIPTION[1], CardTarget.NONE)
                .setColor(JainaEnums.JAINA_COLOR)
                .addOption(CARD_STRINGS.EXTENDED_DESCRIPTION[2], CardTarget.NONE)
                .create();
        this.fadingOut = true;
    }

    // 指向卡牌时显示选项提示
//    @Override
//    public List<TooltipInfo> getCustomTooltips() {
//        return modal.generateTooltips();
//    }

    /**
     * 选择选项之后的操作
     *
     * @param p   玩家
     * @param m   怪物
     * @param opt 选项
     */
    @Override
    public void optionSelected(AbstractPlayer p, AbstractMonster m, int opt) {
        switch (opt) {
            case 0:
                gainPower(new IntangiblePower(p, 1));
                break;
            case 1:
                gainPower(new BufferPower(p, 1));
                break;
            case 2:
                gainPower(new ArtifactPower(p, 1));
                break;
            default:
                throw new IllegalArgumentException("optionSelect ERROR!");
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        modal.open();
    }

    @Override
    public void upp() {
        this.fadingOut = false;
        this.exhaust = true;
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Counterspell();
    }
}
