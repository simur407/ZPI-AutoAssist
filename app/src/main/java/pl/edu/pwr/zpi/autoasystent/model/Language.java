package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.dsl.NotNull;

import java.util.Locale;

/**
 * Określa dostępne języki do wyboru.
 *
 * @author Szymon Bartczak
 * @date 2016-05-30
 */
public enum Language {
    //Nie ma tego w resource poniewaz tego sie nie tlumaczy, a tak mi wygodniej :V
    DEFAULT("Auto", Locale.getDefault()),
    ENGLISH("English", Locale.ENGLISH),
    GERMAN("Deutsch", Locale.GERMAN),
    POLISH("Polski", new Locale("pl"));

    public String language;
    public Locale locale;

    Language(String language, Locale locale) {
        this.language = language;
        this.locale = locale;
    }

    @Override
    public String toString() {
        return language;
    }

    public static Language getLanguageByLocale(@NotNull Locale locale) {
        for (Language l : Language.values()) {
            if(l != DEFAULT && l.locale.getLanguage().equals(locale.getLanguage())) {
                return l;
            }
        }
        return DEFAULT;
    }

}
