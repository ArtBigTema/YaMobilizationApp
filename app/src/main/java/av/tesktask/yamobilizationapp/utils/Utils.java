package av.tesktask.yamobilizationapp.utils;

/**
 * Created by Artem on 01.04.2016.
 */
public class Utils {

    public static int wordCorrector(int n, int single, int few, int many) {
        //see http://www.arabeyes.org/Plural_Forms
        return (n % 10 == 1 && n % 100 != 11) ? single : (n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20)) ? few : many;
    }
}