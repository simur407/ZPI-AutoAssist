package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelingAddPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.RefuelingAddPanel;

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
        //TODO dodać date pickera
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
        Refueling refueling = new Refueling();
        refueling.setQuantity(Integer.parseInt(quantityField.getText().toString()));
        refueling.setRefuelingCost(Double.parseDouble(costField.getText().toString()));
        refueling.setRefuelingMileage(Integer.parseInt(mileageField.getText().toString()));
        refueling.setRefuelingDescription(descriptionField.getText().toString());
        try {
            refueling.setRefuelingDate(DateUtils.stringToDate(dateField.getText().toString(), DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            Logger.error(e);
        }
        presenter.saveRefueling(refueling);
        finish();

    }
}
