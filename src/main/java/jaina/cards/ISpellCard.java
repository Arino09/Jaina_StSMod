package jaina.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;
import java.util.List;

public interface ISpellCard {

    static List<String> getCardDescriptors(String id) {
        List<String> tags = new ArrayList<>();
        tags.add(CardCrawlGame.languagePack.getUIString(id).TEXT[0]);
        return tags;
    }

}
