package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rafalzajfert.androidlogger.Logger;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Language;
import pl.edu.pwr.zpi.autoasystent.presenters.SettingsPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.utils.SettingsUtils;
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
    private ArrayAdapter<Language> adapter;
    private TextView currentReminderTime;
    private SettingsPresenter presenter;
    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            presenter.changeLanguage((Language) parent.getItemAtPosition(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private View.OnClickListener onChangeTimeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.changeReminderTime();
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

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Language.values());
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(onItemSelectedListener);

        Language language = SettingsUtils.getLanguage(this);
        languageSpinner.setSelection(adapter.getPosition(language));

        setCurrentReminderTime();

        changeTime.setOnClickListener(onChangeTimeClickListener);
    }

    @Override
    public void setCurrentReminderTime() {
        int time = SettingsUtils.getReminderTime(this);
        currentReminderTime.setText(DateUtils.timeToString(time));
    }

    @Override
    public void showToast(@StringRes int message) {
        Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void saveLanguageToPref(Language language) {
        SettingsUtils.putLanguage(this, language);
    }

    @Override
    public void showTimePickerDialog() {
        int time = SettingsUtils.getReminderTime(this);
        TimePickerDialog dialog = new TimePickerDialog(new ContextThemeWrapper(this, R.style.AppTheme_Dialog), new TimePickerDialog
                .OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                presenter.saveReminderTime(SettingsActivity.this, hourOfDay, minute);
                Logger.debug(hourOfDay, minute);
            }
        }, time/100, time%100, true);
        dialog.show();
    }
}
