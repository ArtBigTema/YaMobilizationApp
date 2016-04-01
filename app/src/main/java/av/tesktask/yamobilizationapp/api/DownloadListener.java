package av.tesktask.yamobilizationapp.api;

import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;

/**
 * Created by Artem on 01.04.2016.
 */
public interface DownloadListener {//FIXME

    public void doFinalActions(List<Artist> artists);

    public void doErrorActions(String message);
}
