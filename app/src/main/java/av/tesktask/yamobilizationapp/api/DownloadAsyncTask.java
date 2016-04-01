package av.tesktask.yamobilizationapp.api;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 01.04.2016.
 */
public class DownloadAsyncTask extends AsyncTask<String, Void, Void> {
    private DownloadListener downloadListener;
    private String message = "";
    private List<Artist> artists = Collections.emptyList();

    public DownloadAsyncTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(params[0])
                .build();
        Response responses = null;

        try {
            responses = client.newCall(request).execute();
            parseArtists(responses.body().string());
        } catch (IOException e) {
            message = e.getMessage();
            e.printStackTrace();//FIXME
            return null;
        }
        return null;
    }

    private void parseArtists(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            artists = new ArrayList<>(jsonArray.length());

            Gson gson = new GsonBuilder().create();
            Type listType = new TypeToken<List<Artist>>() {
            }.getType();

            artists = (List<Artist>) gson.fromJson(json, listType);
        } catch (JSONException jsonError) {
            message = jsonError.getMessage();
            jsonError.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (downloadListener != null) {
            if (message.isEmpty()) {
                downloadListener.doFinalActions(artists);
            } else {
                downloadListener.doErrorActions("");
            }
        }
    }
}