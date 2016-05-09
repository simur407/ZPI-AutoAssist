package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.presenters.MotAddPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.MotAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;
import pl.edu.pwr.zpi.autoasystent.view.service.TestService;

/**
 * Created by Marek on 09.05.2016.
 */
public class MotAddActivity extends BaseActivity implements MotAddPanel {
    private EditText date, description;
    protected MotAddPresenter presenter;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carId = Long.valueOf(getIntent().getData().toString());
        setContentView(R.layout.fragment_mot_add);
        presenter = new MotAddPresenter(this, carId);
        date = (EditText) findViewById(R.id.mot_date);
        description = (EditText) findViewById(R.id.mot_description);

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    presenter.showDateDialog(new Date());
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveMot();
            default:
                super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void saveMot() {
        Mot mot = new Mot();
        try {
            mot.setMotDate(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            Logger.error(e);
        }

        mot.setMotDescription(description.getText().toString());
        createReminder();
        presenter.saveMot(mot);
        finish();
    }

    @Override
    public void showDateDialog(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MotAddActivity.this.date.setText(dayOfMonth+"." +monthOfYear + "." +year);//TODO CHANGE
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }

    public void createReminder() {
        Intent intent = new Intent(this, TestService.class);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pIntent = PendingIntent.getService(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 5, 9, 19, 20);

        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
    }
}