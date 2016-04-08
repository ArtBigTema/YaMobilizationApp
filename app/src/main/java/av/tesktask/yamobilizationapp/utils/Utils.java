package av.tesktask.yamobilizationapp.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;

/**
 * Created by Artem on 01.04.2016.
 */
public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static int wordCorrector(int n, int single, int few, int many) {
        //see http://www.gnu.org/software/gettext/manual/html_node/Plural-forms.html
        return (n % 10 == 1 && n % 100 != 11) ? single : (n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20)) ? few : many;
    }

    public static List<Artist> parseArtists(String json) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Artist>>() {
        }.getType();

        try {
            return gson.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null; //or Collections.emptyList()
    }

    public static Artist parseArtist(String json) {
        try {
            return new Gson().fromJson(json, Artist.class);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public static String getJson(Object object) {
        return new Gson().toJson(object);
    }
}