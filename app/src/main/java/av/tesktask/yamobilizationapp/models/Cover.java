package av.tesktask.yamobilizationapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Artem on 01.04.2016.
 */
public class Cover implements Parcelable {
    private String small;
    private String big;

    private Cover(Parcel in) {
        small = in.readString();
        big = in.readString();
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

    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(big);
    }
}