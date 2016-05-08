package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelingAddPresenter;
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
                refueling.setRefuelingDate(DateUtils.stringToDate(dateField.getText().toString(), DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                Logger.error(e);
            }
            // !Date picker
        }

        if (!error) {
            presenter.saveRefueling(refueling);
            finish();
        }
    }

    public void onStart() {
        super.onStart();

        EditText txtDate = (EditText) findViewById(R.id.refuel_date);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    DateDialog dialog = new DateDialog(view);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    dialog.show(ft, "Wybierz datę");
                }
            }

        });
    }
}
