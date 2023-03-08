package jaina.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.FrozenPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import static jaina.modCore.IHelper.UI_STRINGS;
import static jaina.modCore.JainaEnums.FROZEN;

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
            if (__instance.hasPower(FrozenPower.POWER_ID)) {
                if (__instance.getPower(FrozenPower.POWER_ID).amount == 3) {
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
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
                    if (m.getClassName().equals("com.megacrit.cardcrawl.monsters.AbstractMonster")
                            && m.getMethodName().equals("takeTurn")) {
                        m.replace("if (!m.hasPower(\"jaina:FrozenPower\") || " +
                                "m.getPower(\"jaina:FrozenPower\").amount != 3) {$_ = $proceed($$);}");
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
                return SpireReturn.Return(ImageMaster.loadImage("jaina/img/UI/intent/frozenL.png"));
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

        public UpdateIntentTip() {
        }

        // 修改 Frozen Intent 的描述
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractMonster __instance, PowerTip ___intentTip) {
            if (__instance.intent == FROZEN) {
                ___intentTip.header = UI_STRINGS.TEXT[0];
                ___intentTip.body = UI_STRINGS.TEXT[1];
                ___intentTip.img = ImageMaster.loadImage("jaina/img/UI/intent/frozen.png");
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
