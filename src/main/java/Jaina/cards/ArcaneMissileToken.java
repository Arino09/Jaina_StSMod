package Jaina.cards;

public class ArcaneMissleToken extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("ArcaneMissleToken");
    private static final CardString CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;
    public ArcaneMissleToken() {
        super(ID, true, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(2);
    }

    @Override
    public void use() {
        dealDamage(target, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }
    
}
