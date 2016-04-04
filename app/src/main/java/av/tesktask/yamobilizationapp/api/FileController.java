package av.tesktask.yamobilizationapp.api;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import av.tesktask.yamobilizationapp.utils.Constants;

/**
 * Created by Artem on 04.04.2016.
 */
public class FileController {

    public static void writeArtistsListToFile(Context context, Object json) throws IOException {
        FileOutputStream fos = context.openFileOutput(Constants.JSON_FILE, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        objectOutputStream.writeObject(json);
        objectOutputStream.close();
        fos.close();
    }

    public static String readArtistsListFromFile(Context context) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(Constants.JSON_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        ois.close();
        fis.close();
        return object.toString();
    }

    public static boolean fileIsExist(Context context) {
        File file = context.getFileStreamPath(Constants.JSON_FILE);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}