package Jaina.relics;

import Jaina.ModCore.IHelper;

public class ArchmageStuff extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("ArchmageStuff");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public ArchmageStuff() {
        super(ID, false, RELIC_STRINGS, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.ActionManager.player.hand.add(randomCard);
    }
    
    @Override
	public AbstractRelic makeCopy() {
		return new ArchmageStuff();
	}
}