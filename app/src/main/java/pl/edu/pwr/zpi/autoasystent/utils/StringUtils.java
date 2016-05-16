package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by Marek on 15.05.2016.
 */
public class StringUtils {

    public static String getStringFromId(Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }
}