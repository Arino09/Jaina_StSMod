package jaina.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jaina.powers.unique.VaporizePower;
import jaina.powers.unique.WildfirePower;

public class AbstractJainaPower extends AbstractPower {

    public AbstractJainaPower(String id, boolean useTestArt, String name, PowerType type) {
        this.ID = id;
        this.name = name;
        this.type = type;
        if (useTestArt) {
            loadRegion("test");
            return;
        }
        // 处理【野火】和【蒸发】的ID
        if (id.equals(WildfirePower.POWER_ID_P)) {
            id = WildfirePower.POWER_ID;
        }
        if (id.equals(VaporizePower.POWER_ID_P)) {
            id = VaporizePower.POWER_ID;
        }
        loadRegion(id.substring(6));
    }

    /**
     * 加载图标
     *
     * @param id 能力ID的后半段，输入test以使用测试图标
     */
    @Override
    public void loadRegion(String id) {
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(
                "jaina/img/powers/" + id + "32.png"), 0, 0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(
                "jaina/img/powers/" + id + "84.png"), 0, 0, 84, 84);
    }
}
