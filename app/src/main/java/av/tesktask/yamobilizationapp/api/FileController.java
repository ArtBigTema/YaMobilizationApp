package av.tesktask.yamobilizationapp.api;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import av.tesktask.yamobilizationapp.utils.Constants;

/**
 * Created by Artem on 04.04.2016.
 */
public class FileController {

    public static void writeJsonToFileIfNeed(Context context, Object json) throws IOException, ClassNotFoundException {
        if (!fileIsExist(context)) {//TODO single if
            writeJsonToFile(context, json);
        } else {
            if (!compareJsonFile(context, json)) {
                writeJsonToFile(context, json);
            }
        }
    }

    public static boolean compareJsonFile(Context context, Object newJson) throws IOException, ClassNotFoundException {
        return readJsonFromFile(context).hashCode() == newJson.hashCode();
    }

    public static void writeJsonToFile(Context context, Object json) throws IOException {
        FileOutputStream fos = context.openFileOutput(Constants.JSON_FILE, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(json);
        oos.close();
        fos.close();
    }

    public static Object readJsonFromFile(Context context) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(Constants.JSON_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        ois.close();
        fis.close();
        return object;
    }

    public static boolean fileIsExist(Context context) {
        File file = context.getFileStreamPath(Constants.JSON_FILE);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}