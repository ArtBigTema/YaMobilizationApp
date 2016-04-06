package av.tesktask.yamobilizationapp.api;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import av.tesktask.yamobilizationapp.models.Artist;

import av.tesktask.yamobilizationapp.utils.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 01.04.2016.
 */
public class DownloadAsyncTask extends AsyncTask<String, Void, Void> {
    private DownloadListener downloadListener;
    private Context context;
    private String errorMessage = "";
    private List<Artist> artists = Collections.emptyList();
    private OkHttpClient client;

    public DownloadAsyncTask(DownloadListener downloadListener, Context context) {
        this.downloadListener = downloadListener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        client = new OkHttpClient.
                Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    @Override
    protected Void doInBackground(String... params) {
        Request request = new Request.Builder()
                .url(params[0])
                .build();
        Response responses = null;

        try {
            responses = client.newCall(request).execute();
            if (responses.isSuccessful()) {
                String json = responses.body().string();

                FileController.writeJsonToFileIfNeed(context, json);//TODO remove if need

                parseArtists(json);
            } else {
                errorMessage = responses.message();
            }
        } catch (IOException e) {
            errorMessage = e.getMessage();
            e.printStackTrace();//FIXME
            return null;
        } catch (ClassNotFoundException e) {
            errorMessage = e.getMessage();
            e.printStackTrace();//FIXME
            return null;
        }
        return null;
    }

    private void parseArtists(String json) {
        artists = Utils.parseArtists(json);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (downloadListener != null) {
            if (errorMessage.isEmpty()) {
                downloadListener.onSuccess(artists);
            } else {
                downloadListener.onError(errorMessage);//FIXME
            }
        }
    }
}