package Jaina.relics;

public class AbstractJainaRelic extends CustomRelic {
    public AbstractJainaRelic(String ID, boolean useTestArt, RelicStrings relicStrings, RelicTier tier, LandingSound landingSound) {
        String[] img_path = getImgPath(ID.substring(6), useTestArt);
        super(ID, ImageMaster.loadImage(img_path[0]), ImageMaster.loadImage(img_path[1]), 
        RelicTier.STARTER, AbstractRelic.LandingSound.FLAT);
        this.name = relicStrings.NAME;
        this.description = relicStrings.DESCRIPTION[0];
    }
    /**
     *
     * @param name 
     */
    private static String[] getImgPath(String name, boolean useTestArt) {
        String[] img_path = new String[2];
        if(useTestArt) {
            name = "test";
        } else {
            img_path[0] = "Jaina/img/relics/" + name + ".png";
            img_path[1] = "Jaina/img/relics/" + name + "_OTL.png";
        }
    }
}