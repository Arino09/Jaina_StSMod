package Jaina.ModCore;

public interface IHelper {
    /**
     *
     * @param id 卡牌/遗物/药水id
     * @return 加上"jaina:"前缀的ID，也是本地化json中的ID
     */
    static String makeID(String id) {
        return "jaina:" + id;
    }

}
