package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import Jaina.ModCore.TempCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

import java.util.ArrayList;

public class Counterspell extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Counterspell");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);


    private static final String s = "Counterspell_";
    private static final String ID_1 = IHelper.makeID(s + "Intangible");
    private static final String ID_2 = IHelper.makeID(s + "Buffer");
    private static final String ID_3 = IHelper.makeID(s + "Artifact");
    private static final CardStrings DES_1 = CardCrawlGame.languagePack.getCardStrings(ID_1);
    private static final CardStrings DES_2 = CardCrawlGame.languagePack.getCardStrings(ID_2);
    private static final CardStrings DES_3 = CardCrawlGame.languagePack.getCardStrings(ID_3);

    private static final int COST = 2;

    public Counterspell() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.fadingOut = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPlayer player = AbstractDungeon.player;
        ArrayList<AbstractCard> options = new ArrayList<>();
        // 添加三张临时卡牌
        options.add(new TempCard(ID_1, DES_1, CardType.POWER, JainaEnums.JAINA_COLOR));
        options.add(new TempCard(ID_2, DES_2, CardType.POWER, JainaEnums.JAINA_COLOR));
        options.add(new TempCard(ID_3, DES_3, CardType.POWER, JainaEnums.JAINA_COLOR));
        // 回调函数：加入到手牌
        this.addToBot(new SelectCardsCenteredAction(options, "选择一种效果", c -> {
            switch (c.get(0).cardID.substring(19)) {
                case "Intangible":
                    gainPower(new IntangiblePower(player, 1));
                    break;
                case "Buffer":
                    gainPower(new BufferPower(player, 1));
                    break;
                case "Artifact":
                    gainPower(new ArtifactPower(player, 1));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }));
    }
            @Override
    public void upp() {
        this.fadingOut = false;
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Counterspell();
    }
}
