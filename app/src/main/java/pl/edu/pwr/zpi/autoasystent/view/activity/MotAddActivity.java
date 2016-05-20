package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.presenters.MotAddPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.utils.ReminderBuilder;
import pl.edu.pwr.zpi.autoasystent.view.MotAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

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

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDateDialog(new Date());//TODO temp date
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
            mot.setMotDate(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_FORMAT_DEF));
        } catch (ParseException e) {
            Logger.error(e);
        }

        mot.setMotDescription(description.getText().toString());
        presenter.saveMot(mot);
        finish();
    }

    @Override
    public void showDateDialog(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MotAddActivity.this.date.setText(dayOfMonth+"." +(monthOfYear+1) + "." +year);//TODO CHANGE
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }

    public void createReminder() {
        try {
            new ReminderBuilder(this)
                    .setDate(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_PATTERN))
                    .setTitle("Testowo")
                    .setDescription("Z buildera!")
                    .set();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}