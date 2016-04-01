package av.tesktask.yamobilizationapp.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import av.tesktask.yamobilizationapp.utils.Constants;

/**
 * Created by Artem on 01.04.2016.
 */
public class HttpApi {
    private static volatile HttpApi instance;
    public static final String SCHEME = "h +ft +ft +fp";
    public static final String HOST = "d +fo +fw +fn +fl +fo +fa +fd +f. +fc +fd +fn +f. +fy +fa +fn +fd +fe +fx +f. +fn +fe +ft";
    public static final String PATH = "m +fo +fb +fi +fl +fi +fz +fa +ft +fi +fo +fn +f- +f2 +f0 +f1 +f6";
    public static final String JSON = "a +fr +ft +fi +fs +ft +fs +f. +fj +fs +fo +fn";

    private HttpApi() {

    }

    public static HttpApi getInstance() {
        HttpApi localInstance = instance;
        if (localInstance == null) {
            synchronized (HttpApi.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new HttpApi();
                }
            }
        }
        return localInstance;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void execute(DownloadListener downloadListener) {
        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(downloadListener);
        downloadAsyncTask.execute(getUri());
    }

    public String getUri() {
        Uri.Builder builder = new Uri.Builder();//FIXME remove replace
        builder.scheme(Constants.SCHEME.replaceAll(" \\+f", ""))
                .authority(Constants.HOST.replaceAll(" \\+f", ""))
                .appendPath(Constants.PATH.replaceAll(" \\+f", ""))
                .appendPath(Constants.JSON.replaceAll(" \\+f", ""));
        return builder.build().toString();
    }
}