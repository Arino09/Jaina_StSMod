package jaina.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractJainaPower extends AbstractPower {

    protected static final Logger logger = LogManager.getLogger(AbstractJainaPower.class.getName());

    public AbstractJainaPower(String id, boolean useTestArt, String name, PowerType type) {
        this.ID = id;
        this.name = name;
        this.type = type;
        if (useTestArt) {
            loadRegion("test");
            return;
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
