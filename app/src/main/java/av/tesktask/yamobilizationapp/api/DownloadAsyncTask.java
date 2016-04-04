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
import java.util.concurrent.TimeUnit;

import av.tesktask.yamobilizationapp.models.Artist;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 01.04.2016.
 */
public class DownloadAsyncTask extends AsyncTask<String, Void, Void> {
    private DownloadListener downloadListener;
    private String errorMessage = "";
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
        OkHttpClient client = new OkHttpClient.
                Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(params[0])
                .build();
        Response responses = null;

        try {
            responses = client.newCall(request).execute();
            if (responses.isSuccessful()) {
                parseArtists(responses.body().string());
            } else {
                errorMessage = responses.message();
            }
        } catch (IOException e) {
            errorMessage = e.getMessage();
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

            artists = gson.fromJson(json, listType);
        } catch (JSONException jsonError) {
            errorMessage = jsonError.getMessage();
            jsonError.printStackTrace();//TODO to log.d
        }
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