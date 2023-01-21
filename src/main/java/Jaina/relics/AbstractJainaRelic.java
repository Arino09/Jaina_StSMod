package Jaina.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class AbstractJainaRelic extends CustomRelic {

    public AbstractJainaRelic(String ID, boolean useTestArt, RelicStrings relicStrings, RelicTier tier, LandingSound landingSound) {
        super(ID, ImageMaster.loadImage(getImgPath(ID, useTestArt)[0]), ImageMaster.loadImage(getImgPath(ID, useTestArt)[1]), tier, landingSound);
        this.description = relicStrings.DESCRIPTIONS[0];
    }

    /**
     * 根据遗物名获得图像路径
     * @param ID 遗物ID
     */
    private static String[] getImgPath(String ID, boolean useTestArt) {
        String[] img_path = new String[2];
        if(useTestArt) {
            ID = "test";
        } else {
            ID = ID.substring(6);
        }
        img_path[0] = "Jaina/img/relics/" + ID + ".png";
        img_path[1] = "Jaina/img/relics/" + ID + "_OTL.png";
        return img_path;
    }
}