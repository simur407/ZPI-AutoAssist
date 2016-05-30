package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.rafalzajfert.androidlogger.Logger;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Language;
import pl.edu.pwr.zpi.autoasystent.presenters.SettingsPresenter;
import pl.edu.pwr.zpi.autoasystent.view.SettingsPanel;

/**
 * Aktywność ustawień.
 *
 * @author Szymon Bartczak
 * @date 2016-05-21
 */
public class SettingsActivity extends BaseActivity implements SettingsPanel {

    private Button changeTime, exportButton, importButton, deleteButton;
    private Spinner languageSpinner;
    private TextView currentReminderTime;
    private SettingsPresenter presenter;
    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Logger.debug(parent.getItemAtPosition(position));
            presenter.changeLanguage((Language) parent.getItemAtPosition(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
        currentReminderTime = (TextView) findViewById(R.id.settings_current_time);
        changeTime = (Button) findViewById(R.id.change_time_button);
        exportButton = (Button) findViewById(R.id.export_database_button);
        importButton = (Button) findViewById(R.id.import_database_button);
        deleteButton = (Button) findViewById(R.id.delete_database_button);

        presenter = new SettingsPresenter(this);

        languageSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Language.values()));
        languageSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }
}
