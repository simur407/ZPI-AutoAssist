package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-05-15
 */
public class StringUtils {

    public static String getStringFromId(Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }
}
