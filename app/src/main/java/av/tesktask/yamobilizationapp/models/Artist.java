package av.tesktask.yamobilizationapp.models;

/**
 * Created by Artem on 31.03.2016.
 */
public class Artist implements Comparable<Artist> {
    private static String LOG_TAG = Artist.class.getName();

    private long id;
    private String name;
    private String[] genres;
    private int tracks;
    private int albums;
    private int link;
    private int description;
    private String bigCover;
    private String smallCover;

    public Artist(long id, String name, String[] genres, int tracks, int albums, int link, int description) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.albums = albums;
        this.link = link;
        this.description = description;
    }

    public static Artist constructArtist() {//FIXME
        return new Artist(System.currentTimeMillis(), String.valueOf(System.currentTimeMillis()), null, 0, 0, 0, 0);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public String getSmallCover() {
        return smallCover;
    }

    public String getBigCover() {
        return bigCover;
    }

    public void setCover(String sCover, String bCover) {
        this.smallCover = sCover;
        this.bigCover = bCover;
    }

    @Override
    public int compareTo(Artist another) {
        return this.name.compareTo(another.name);
    }
}