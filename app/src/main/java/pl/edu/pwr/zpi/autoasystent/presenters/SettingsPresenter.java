package pl.edu.pwr.zpi.autoasystent.presenters;

import android.content.Context;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Language;
import pl.edu.pwr.zpi.autoasystent.utils.SettingsUtils;
import pl.edu.pwr.zpi.autoasystent.view.SettingsPanel;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-05-30
 */
public class SettingsPresenter {

    private SettingsPanel panel;

    public SettingsPresenter(SettingsPanel panel) {
        this.panel = panel;
    }

    public void changeLanguage(Language language) {
        panel.saveLanguageToPref(language);
    }

    public void changeReminderTime() {
        panel.showTimePickerDialog();
    }

    public void saveReminderTime(Context context, int hourOfDay, int minute) {
        SettingsUtils.putReminderTime(context, hourOfDay*100 + minute);
        panel.setCurrentReminderTime();
        panel.showToast(R.string.new_reminder_time_set);
    }
}
