package jaina.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;
import java.util.List;

public interface ISpellCard {

    static List<String> getCardDescriptors(String id) {
        List<String> tags = new ArrayList<>();
        tags.add(CardCrawlGame.languagePack.getUIString(id).TEXT[0]);
        return tags;
    }

    static List<TooltipInfo> getCustomTooltipsTop(String id) {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(BaseMod.getKeywordTitle(id), BaseMod.getKeywordDescription(id)));
        return tips;
    }
}
