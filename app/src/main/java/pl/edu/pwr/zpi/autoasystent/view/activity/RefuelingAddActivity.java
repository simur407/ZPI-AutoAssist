package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelingAddPresenter;
import pl.edu.pwr.zpi.autoasystent.service.CarService;

public class RefuelingAddActivity extends BaseActivity {

    protected TextView dateField;
    protected TextView mileageField;
    protected TextView costField;
    protected TextView quantityField;
    protected TextView descriptionField;
    protected RefuelingAddPresenter presenter;
    protected int carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refueling_add);
        //TODO odczytać carId z innego miejsca i dodać date pickera
        carId = 1;
        dateField = (TextView) findViewById(R.id.refuel_date);
        mileageField = (TextView) findViewById(R.id.refuel_mileage);
        costField = (TextView) findViewById(R.id.refuel_cost);
        quantityField = (TextView) findViewById(R.id.refuel_quantity);
        descriptionField = (TextView) findViewById(R.id.refuel_description);

        presenter = new RefuelingAddPresenter(this);

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
        refueling.setCar(CarService.getInstance().findCarById(carId));
        if (quantityField.length() < 1) {
            error = true;
            quantityField.setError(getString(R.string.error));
        } else {
            refueling.setQuantity(Integer.parseInt(quantityField.getText().toString()));
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
        } else {
            refueling.setRefuelingMileage(Integer.parseInt(mileageField.getText().toString()));
        }
        refueling.setRefuelingDescription(descriptionField.getText().toString());

        String dateString = dateField.getText().toString();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(dateString);
            refueling.setRefuelingDate(date);
            if (!error) {
                presenter.saveRefueling(refueling);
                finish();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
