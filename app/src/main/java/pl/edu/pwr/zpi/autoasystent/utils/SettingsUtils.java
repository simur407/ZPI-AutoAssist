package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.Context;
import android.content.SharedPreferences;

import pl.edu.pwr.zpi.autoasystent.model.Language;

/**
 * Klasa wspomagająca pobieranie i zapisywanie ustawień.
 *
 * @author Szymon Bartczak
 * @date 2016-05-30
 */
public class SettingsUtils {
    private static final String SETTINGS_SHARED_KEY = "pl.edu.pwr.zpi.autoasystent.settings";
    private static final String LANGUAGE_KEY = "language";
    private static final String REMINDER_TIME_KEY = "reminder_time";
    private static final int DEFAULT_REMINDER_TIME = 1200;

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(SETTINGS_SHARED_KEY, Context.MODE_PRIVATE);
    }

    public static void setChangeListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getSharedPrefs(context).registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unsetChangeListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getSharedPrefs(context).unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static void putLanguage(Context context, Language language) {
        getSharedPrefs(context).edit().putString(LANGUAGE_KEY, language.name()).commit();
    }

    public static Language getLanguage(Context context) {
        return Language.valueOf(getSharedPrefs(context).getString(LANGUAGE_KEY, Language.DEFAULT.name()));
    }

    /**
     * Czas przypomnienia w formacie HHmm jako jedna liczba.
     */
    public static int getReminderTime(Context context) {
        return getSharedPrefs(context).getInt(REMINDER_TIME_KEY, DEFAULT_REMINDER_TIME);
    }

    public static void putReminderTime(Context context, int time) {
        getSharedPrefs(context).edit().putLong(REMINDER_TIME_KEY, time).commit();
    }
}
