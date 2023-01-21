package Jaina.relics;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import Jaina.cards.AbstractJainaCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class ArchmageStuff extends AbstractJainaRelic {
    public static final String ID = IHelper.makeID("ArchmageStuff");
    private static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public ArchmageStuff() {
        super(ID, false, RELIC_STRINGS, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        ArrayList<AbstractCard> cardRng = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards()) {
            // 随机卡池为角色卡、非稀有、非基本、非治疗卡
            if(c.color.equals(JainaEnums.JAINA_COLOR) && !c.rarity.equals(AbstractCard.CardRarity.RARE)
                    && !c.rarity.equals(AbstractCard.CardRarity.BASIC) && !c.hasTag(AbstractCard.CardTags.HEALING))
            cardRng.add(c);
        }
        // 从随机卡池中选一张卡
        AbstractCard card = cardRng.get(AbstractDungeon.cardRandomRng.random(cardRng.size() - 1)).makeCopy();
        addToBot(new MakeTempCardInHandAction(card));
    }

    @Override
	public AbstractRelic makeCopy() {
		return new ArchmageStuff();
	}
}