package pl.edu.pwr.zpi.autoasystent.presenters;

import android.view.View;
import android.widget.AdapterView;

import pl.edu.pwr.zpi.autoasystent.model.Achievement;
import pl.edu.pwr.zpi.autoasystent.view.AchievementPanel;

/**
 * Created by Marek on 16.05.2016.
 */
public class AchievementPresenter {
    AchievementPanel panel;

    public AchievementPresenter(AchievementPanel panel) {
        this.panel = panel;
    }

    public void onGridItemClick(AdapterView<?> parent, View view, int position, long id) {
        Achievement achievement = Achievement.values()[position];
        panel.openDialog(achievement.getId());
    }
}
