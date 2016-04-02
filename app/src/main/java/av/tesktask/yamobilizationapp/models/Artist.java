package av.tesktask.yamobilizationapp.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

import av.tesktask.yamobilizationapp.R;
import av.tesktask.yamobilizationapp.utils.Utils;

/**
 * Created by Artem on 31.03.2016.
 */
public class Artist implements Parcelable, Comparable<Artist> {
    public static String TAG = Artist.class.getName();

    private long id;
    private String name;
    private String[] genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;

    private Artist(Parcel in) {
        id = in.readLong();
        name = in.readString();
        genres = in.createStringArray();
        tracks = in.readInt();
        albums = in.readInt();
        link = in.readString();
        description = in.readString();
        cover = in.readParcelable(Cover.class.getClassLoader());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getGenresSingleLine() {
        return Arrays.toString(genres).replaceAll("[\\[\\]]", "").trim();//FIXME if length == 0
    }

    public int getTracks() {
        return tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public String getSummary(Context context) {
        StringBuilder s = new StringBuilder();

        s.append(getTracks());
        s.append(" ");
        s.append(context.getResources().getString(Utils.wordCorrector(getTracks(),
                R.string.artist_song_single, R.string.artist_song_few, R.string.artist_song_many)));
        s.append(", ");
        s.append(getAlbums());
        s.append(" ");
        s.append(context.getResources().getString(Utils.wordCorrector(getAlbums(),
                R.string.artist_album_single, R.string.artist_album_few, R.string.artist_album_many)));

        return s.toString();
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getSmallCover() {
        return cover.getSmallCover();
    }

    public String getBigCover() {
        return cover.getBigCover();
    }

    @Override
    public int compareTo(Artist another) {
        return this.name.compareTo(another.name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // ", genres=" + Arrays.toString(genres) +
                // ", tracks=" + tracks +
                // ", albums=" + albums +
                ", link=" + link +
                //  ", description=" + description +
                ", bigCover='" + getBigCover() + '\'' +
                ", smallCover='" + getSmallCover() + '\'' +
                '}';
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeStringArray(genres);
        dest.writeInt(tracks);
        dest.writeInt(albums);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeParcelable(cover, flags);
    }
}