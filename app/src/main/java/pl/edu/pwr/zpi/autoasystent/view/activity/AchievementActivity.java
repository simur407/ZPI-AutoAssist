package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.AchievementPresenter;
import pl.edu.pwr.zpi.autoasystent.view.AchievementPanel;
import pl.edu.pwr.zpi.autoasystent.view.adapter.AchievementAdapter;

/**
 * Created by Marek on 16.05.2016.
 */
public class AchievementActivity extends BaseActivity implements AchievementPanel {
    private AchievementAdapter adapter;
    private AchievementPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AchievementAdapter(this);
        presenter = new AchievementPresenter(this);
        setContentView(R.layout.fragment_achievements);
        GridView gridView = (GridView) findViewById(R.id.achievements_gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onGridItemClick(parent, view, position, id);
        }
    };

    @Override
    public void openDialog(@StringRes int id) {
        new AlertDialog.Builder(new ContextThemeWrapper(AchievementActivity.this, R.style.AppTheme_Dialog))
                .setMessage(id)
                .show();
    }
}
