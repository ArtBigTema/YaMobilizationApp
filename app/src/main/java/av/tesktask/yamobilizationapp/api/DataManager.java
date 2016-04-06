package av.tesktask.yamobilizationapp.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;
import av.tesktask.yamobilizationapp.utils.Constants;
import av.tesktask.yamobilizationapp.utils.Utils;

/**
 * Created by Artem on 01.04.2016.
 */
public class DataManager {
    private static final String TAG = DataManager.class.getSimpleName();
    private static volatile DataManager instance;

    private DataManager() {
    }
    
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean fileIsExist(Context context) {
        return FileController.fileIsExist(context);
    }

    public List<Artist> readFromFile(Context context) {
        try {
            return Utils.parseArtists(
                    FileController.readJsonFromFile(context).toString());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public void execute(DownloadListener downloadListener, Context context) {
        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(downloadListener, context);
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