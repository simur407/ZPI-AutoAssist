package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelingAddPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.AchievementUtils;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.RefuelingAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

public class RefuelingAddActivity extends BaseActivity implements RefuelingAddPanel {

    protected TextView dateField;
    protected TextView mileageField;
    protected TextView costField;
    protected TextView quantityField;
    protected TextView descriptionField;
    protected RefuelingAddPresenter presenter;
    protected long carId;
    protected AchievementUtils achievementUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refueling_add);

        carId = Long.valueOf(getIntent().getData().toString());
        dateField = (TextView) findViewById(R.id.refuel_date);
        mileageField = (TextView) findViewById(R.id.refuel_mileage);
        costField = (TextView) findViewById(R.id.refuel_cost);
        quantityField = (TextView) findViewById(R.id.refuel_quantity);
        descriptionField = (TextView) findViewById(R.id.refuel_description);

        presenter = new RefuelingAddPresenter(this, carId);

        dateField.setInputType(InputType.TYPE_NULL);
        dateField.setText(DateUtils.dateToString(new Date()));
        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.showDatePicker(DateUtils.stringToDate(dateField.getText().toString(), DateUtils.DATE_FORMAT_DEF));
                } catch (ParseException e) {
                    Logger.error(e);
                }
            }
        });

        setToolbarTitle(R.string.refuel_add_label);
        achievementUtils = new AchievementUtils(this);
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
                saveRefueling();
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void saveRefueling() {
        boolean error = false;
        Refueling refueling = new Refueling();

        if (quantityField.length() < 1) {
            error = true;
            quantityField.setError(getString(R.string.error));
        } else {
            refueling.setQuantity(Double.parseDouble(quantityField.getText().toString()));
        }
        if (costField.length() < 1) {
            error = true;
            costField.setError(getString(R.string.error));
        } else {
            refueling.setRefuelingCost(Double.parseDouble(costField.getText().toString()));
        }
        if (mileageField.length() < 1) {
            error = true;
            mileageField.setError(getString(R.string.error));
        } else if (mileageField.length() > 9) {

            error = true;
            mileageField.setError(getString(R.string.error_value));
        } else {
            refueling.setRefuelingMileage(Integer.parseInt(mileageField.getText().toString()));
        }
        refueling.setRefuelingDescription(descriptionField.getText().toString());
        if (dateField.length() < 1) {
            error = true;
            dateField.setError(getString(R.string.error));
        } else {
            // Date picker
//            String dateString = dateField.getText().toString();
//            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date date = DateUtils.stringToDate(dateField.getText().toString(), DateUtils.DATE_FORMAT_DEF);
                Date today = new Date();
                if (date.after(today)) {
                    error = true;
                    dateField.setError(getString(R.string.error_value));
                } else {
                    dateField.setError(null);
                    refueling.setRefuelingDate(date);
                }
            } catch (ParseException e) {
                Logger.error(e);
            }
            // !Date picker
        }

        if (!error) {
            presenter.saveRefueling(refueling);
            achievementUtils.checkRefueling();
            finish();
        }
    }

    @Override
    public void showDatePicker(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setDate(date);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                RefuelingAddActivity.this.dateField.setText(DateDialog.convertToString(year, monthOfYear, dayOfMonth));
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }
}
