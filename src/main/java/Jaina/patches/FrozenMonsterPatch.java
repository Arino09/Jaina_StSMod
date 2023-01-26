package Jaina.patches;

import Jaina.ModCore.IHelper;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import static Jaina.ModCore.JainaEnums.FROZEN;

public class FrozenMonsterPatch {
    public FrozenMonsterPatch() {
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "rollMove"
    )
    public static class RollMove {
        public RollMove() {
        }

        public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
            return __instance.hasPower("jaina:FrozenPower") ? SpireReturn.Return((Void) null) : SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class GetNextAction {
        public GetNextAction() {
        }

        public static ExprEditor Instrument() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals("com.megacrit.cardcrawl.monsters.AbstractMonster") && m.getMethodName().equals("takeTurn")) {
                        m.replace("if (!m.hasPower(Jaina.powers.FrozenPower.POWER_ID)) {$_ = $proceed($$);}");
                    }
                }
            };
        }
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "getIntentImg"
    )
    public static class GetIntentImg {
        public GetIntentImg() {
        }

        // 修改 Frozen Intent 的图片
        @SpirePrefixPatch
        public static SpireReturn<Texture> Prefix(AbstractMonster __instance) {
            if (__instance.intent == FROZEN) {
                // 提前在原方法中返回
                return SpireReturn.Return(ImageMaster.loadImage("Jaina/img/UI/intent/frozenL.png"));
            }
            // 继续原方法
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "updateIntentTip"
    )
    public static class UpdateIntentTip {

        private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(IHelper.makeID("FrozenIntent"));

        public UpdateIntentTip() {
        }

        // 修改 Frozen Intent 的描述
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractMonster __instance, PowerTip ___intentTip) {
            if (__instance.intent == FROZEN) {
                ___intentTip.header = UI_STRINGS.TEXT[0];
                ___intentTip.body = UI_STRINGS.TEXT[1];
                ___intentTip.img = ImageMaster.loadImage("Jaina/img/UI/intent/frozen.png");
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
