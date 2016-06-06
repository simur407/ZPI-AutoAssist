package pl.edu.pwr.zpi.autoasystent.view;

import android.support.annotation.StringRes;

import pl.edu.pwr.zpi.autoasystent.model.Language;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-05-30
 */
public interface SettingsPanel {
    void saveLanguageToPref(Language language);

    void showTimePickerDialog();

    void setCurrentReminderTime();

    void showToast(@StringRes int message);
}
