package Jaina.cards;

public class ArcaneMissle extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneMissle");
    private static final CardString CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    public ArcaneMissle() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    public void use() {
        ArcaneMissleToken token = ArcaneMissleToken.makeCopy();
        for(int i = 0; i < 3; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(token, true));
        }
    }
    
    @Override
    public void makeCopy() {
        return new ArcaneMissle();
    }
}
