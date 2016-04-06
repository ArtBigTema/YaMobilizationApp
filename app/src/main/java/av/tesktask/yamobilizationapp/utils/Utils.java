package av.tesktask.yamobilizationapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;

/**
 * Created by Artem on 01.04.2016.
 */
public class Utils {

    public static int wordCorrector(int n, int single, int few, int many) {
        //see http://www.arabeyes.org/Plural_Forms
        return (n % 10 == 1 && n % 100 != 11) ? single : (n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20)) ? few : many;
    }

    public static ArrayList<Artist> parseArtists(String json) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Artist>>() {
        }.getType();

        return gson.fromJson(json, listType);//FIXME exception
    }

    public static Artist parseArtist(String json) {
        return new Gson().fromJson(json, Artist.class);//FIXME exception
    }

    public static String getJson(Object object) {
        return new Gson().toJson(object);
    }
}