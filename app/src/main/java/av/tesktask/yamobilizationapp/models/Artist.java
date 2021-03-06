package av.tesktask.yamobilizationapp.models;

import android.content.Context;

import java.util.Arrays;

import av.tesktask.yamobilizationapp.R;
import av.tesktask.yamobilizationapp.utils.Utils;

/**
 * Created by Artem on 31.03.2016.
 */
public class Artist implements Comparable<Artist> {
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

    /**
     * Getter for Artist ID
     * @return long id;
     * */
    public long getId() {
        return id;
    }

    /**
     * Getter for Artist name
     * @return String name;
     * */
    public String getName() {
        return name;
    }

    /**
     * Getter for Artist genres
     * @return String genre;
     * */
    public String getGenresSingleLine() {
        return Arrays.toString(genres).replaceAll("[\\[\\]]", "").trim();//FIXME if length == 0
    }

    /**
     * Checker for Artist genre
     * @return boolean containGenre;
     * */
    public boolean containsGenre(String genre) {
        return Arrays.asList(genres).contains(genre);
    }

    /**
     * Getter for Artist tracks
     * @return int tracks;
     * */
    public int getTracks() {
        return tracks;
    }

    /**
     * Getter for Artist albums
     * @return int albums;
     * */
    public int getAlbums() {
        return albums;
    }

    /**
     * Getter for Artist summary
     * @param context appContext
     * @param divider string divider
     * @return String summary about Artist;
     * */
    public String getSummary(Context context, String divider) {
        StringBuilder summary = new StringBuilder();

        summary.append(getAlbums());
        summary.append(" ");
        summary.append(context.getResources().getString(Utils.wordCorrector(getAlbums(),
                R.string.artist_album_single, R.string.artist_album_few, R.string.artist_album_many)));
        summary.append(divider);
        summary.append(getTracks());
        summary.append(" ");
        summary.append(context.getResources().getString(Utils.wordCorrector(getTracks(),
                R.string.artist_song_single, R.string.artist_song_few, R.string.artist_song_many)));
        return summary.toString();
    }

    /**
     * Getter for Artist link
     * @return String link;
     * */
    public String getLink() {
        return link;
    }

    /**
     * Getter for Artist description
     * @return String description;
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for Artist smallCover
     * @return String cover.small;
     * */
    public String getSmallCover() {
        return cover.getSmallCover();
    }

    /**
     * Getter for Artist bigCover
     * @return String cover.big;
     * */
    public String getBigCover() {
        return cover.getBigCover();
    }

    /**
     * Method for Collection.sort()
     * */
    @Override
    public int compareTo(Artist another) {
        return this.name.compareTo(another.name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", tracks=" + tracks +
                ", albums=" + albums +
                ", link=" + link +
                //  ", description=" + description +
                ", bigCover='" + getBigCover() + '\'' +
                ", smallCover='" + getSmallCover() + '\'' +
                '}';
    }
}