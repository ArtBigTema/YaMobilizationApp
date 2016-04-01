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

    public String getSmallCover() {
        return small;
    }

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