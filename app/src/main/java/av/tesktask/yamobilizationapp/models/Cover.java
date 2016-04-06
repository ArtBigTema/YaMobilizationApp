package av.tesktask.yamobilizationapp.models;

/**
 * Created by Artem on 01.04.2016.
 */
public class Cover {
    private String small;
    private String big;

    public Cover(String small, String big) {
        this.small = small;
        this.big = big;
    }

    /**
     * Getter for Artist smallCover
     * @return String small;
     * */
    public String getSmallCover() {
        return small;
    }

    /**
     * Getter for Artist bigCover
     * @return String big;
     * */
    public String getBigCover() {
        return big;
    }

    @Override
    public String toString() {
        return "Cover{" +
                "small='" + small + '\'' +
                ", big='" + big + '\'' +
                '}';
    }
}