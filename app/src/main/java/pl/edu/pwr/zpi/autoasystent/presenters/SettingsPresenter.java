package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Language;
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
}
