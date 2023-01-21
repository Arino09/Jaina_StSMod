package Jaina.cards;

import Jaina.ModCore.IHelper;

public class CounterSpell extends AbstractJainaCard implements ModalChoice.Callback {

    public static final String ID = IHelper.makeID("CounterSpell");
    private static final CardString CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private ModalChoice modal;
    private static final int COST = 2;

    public ArcaneIntellect() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        modal = new ModalChoiceBuilder()
                .setCallback(this)
                .setColor(CardColor.COLORLESS)
                .addOption("获得一层 无实体 。")
                .setColor(CardColor.COLORLESS)
                .addOption("获得一层 缓冲 。")
                .setColor(CardColor.COLORLESS)
                .addOption("获得一层 人工制品")
    }

    // Uses the titles and descriptions of the option cards as tooltips for this card
    @Override
    public List<TooltipInfo> getCustomToolTips() {
        return modal.generateTooltips();
    }

    /**
     * 选择选项之后的操作
     * @param p 玩家
     * @param m 怪物
     * @param opt 选项
     */
    @Override
    public void optionSelected(AbstractPlayer p, AbstractMonster m, int opt) {
        switch (opt) {
            case 0:
                gainPower(new IntangiblePower(p, p, 1));
                break;
            case 1:
                gainPower(new BufferPower(p, p, 1));
                break;
            case 2:
                gainPower(new ArtifactPower(p, p, 1));
                break;
            default:
                return;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        modal.open();
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
        this.cost = 1;
    }

    @Override
    public void makeCopy() {
        return new CounterSpell();
    }
}
