package av.tesktask.yamobilizationapp.models;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Artem on 31.03.2016.
 */
public class Artist implements Comparable<Artist>, Serializable {
    private static String LOG_TAG = Artist.class.getName();

    private long id;
    private String name;
    private String[] genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;

    public Artist(long id, String name, String[] genres, int tracks, int albums, String link, String description, Cover cover) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.albums = albums;
        this.link = link;
        this.description = description;
        this.cover = cover;
    }

    public static Artist constructArtist() {//FIXME
        return new Artist(System.currentTimeMillis(), String.valueOf(System.currentTimeMillis()), null, 0, 0, "", "", null);
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
        return Arrays.toString(genres);
    }

    public int getTracks() {
        return tracks;
    }

    public int getAlbums() {
        return albums;
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
}