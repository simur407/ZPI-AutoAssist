package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.rafalzajfert.androidlogger.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.AddServicePresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.CarAddServicePanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

public class AddServiceActivity extends BaseActivity implements CarAddServicePanel {


    protected EditText date, mileage, cost, garage, description;
    protected ListView list;
    protected long carId;

    protected AddServicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);

        carId = Long.valueOf(getIntent().getData().toString());
        date = (EditText) findViewById(R.id.service_date);
        mileage = (EditText) findViewById(R.id.service_mileage);
        cost = (EditText) findViewById(R.id.service_cost);
        garage = (EditText) findViewById(R.id.service_garage);
        description = (EditText) findViewById(R.id.service_description);

        date.setInputType(InputType.TYPE_NULL);

        list = (ListView) findViewById(R.id.service_add_list);

        presenter = new AddServicePresenter(this, carId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save: saveService();
            default: super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void saveService() {
        ServiceJobs service = new ServiceJobs();
        boolean error = false;
//        RefersTo refersTo = new RefersTo();

        //TODO Obsługa przekroczenia wartości zmiennych

        //TODO Coś się dzieje z focusem datepickera po błędnym uzupełnieniu


        if (mileage.length() < 1) {
            error = true;
            mileage.setError(getString(R.string.error));
        } else {
            service.setServiceMileage(Integer.parseInt(mileage.getText().toString()));
        }
        if (cost.length() < 1) {
            error = true;
            cost.setError(getString(R.string.error));
        } else {
            service.setServiceCost(Double.parseDouble(cost.getText().toString()));
        }
        if (date.length() < 1) {
            error = true;
            date.setError(getString(R.string.error));
        } else {
            // Date picker
            try {
                service.setServiceDate(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                Logger.error(e);
            }
            // !Date picker
        }

        service.setServiceGarage(garage.getText().toString());
        service.setServiceDescription(description.getText().toString());

        //TODO Lista napraw
//        ArrayList<String> serviceList = new ArrayList<String>();
//
//        for(String element : serviceList)
//            refersTo.getMaintenance().setMaintenanceName(element.toString());
//        }
        if (!error) {
            presenter.saveService(service);
            finish();
        }
    }

    public void onStart() {
        super.onStart();

        EditText txtDate = (EditText) findViewById(R.id.service_date);
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


    @Override
    public void setMaintanaceList(List<CarMaintenance> maintenances) {

    }

    @Override
    public void showDataPicker(int currentData) {

    }

    @Override
    public void setCurrentData() {

    }
}
