package Jaina.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.helpers.ImageMaster;
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

        @SpireInsertPatch(rloc = 0)
        public static Texture Insert(AbstractMonster __instance) {
            if(__instance.intent == FROZEN) {
                return ImageMaster.loadImage("Jaina/img/powers/test84.png");
            }
            return null;
        }
    }
}
