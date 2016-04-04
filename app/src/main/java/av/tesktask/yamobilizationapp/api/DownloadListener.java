package av.tesktask.yamobilizationapp.api;

import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;

/**
 * Created by Artem on 01.04.2016.
 */
public interface DownloadListener {//FIXME

    void onSuccess(List<Artist> artists);

    void onError(String message);
}