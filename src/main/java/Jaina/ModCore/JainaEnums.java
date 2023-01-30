package Jaina.ModCore;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class JainaEnums {
    @SpireEnum(name = "JAINA")
    public static AbstractCard.CardColor JAINA_COLOR;
    @SpireEnum(name = "JAINA")
    public static CardLibrary.LibraryType JAINA_LIBRARY;
    @SpireEnum
    public static AbstractPlayer.PlayerClass JAINA_CLASS;
    @SpireEnum
    public static AbstractMonster.Intent FROZEN;
    @SpireEnum
    public static AbstractCard.CardTags FIRE;
    @SpireEnum
    public static AbstractCard.CardTags FROST;
    @SpireEnum
    public static AbstractCard.CardTags ARCANE;
}
