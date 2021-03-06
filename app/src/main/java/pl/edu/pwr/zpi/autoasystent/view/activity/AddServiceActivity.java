package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.AddServicePresenter;
import pl.edu.pwr.zpi.autoasystent.utils.AchievementUtils;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.utils.NonScrollListView;
import pl.edu.pwr.zpi.autoasystent.view.CarAddServicePanel;
import pl.edu.pwr.zpi.autoasystent.view.adapter.CarMaintenanceAdapter;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

public class AddServiceActivity extends BaseActivity implements CarAddServicePanel {


    protected EditText date, mileage, cost, garage, description;
    protected NonScrollListView maintenancesList;
    protected long carId;
    protected CarMaintenanceAdapter adapter;
    //  protected View listLayout;

    protected AddServicePresenter presenter;
    protected AchievementUtils achievementUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        adapter = new CarMaintenanceAdapter(this);
        carId = Long.valueOf(getIntent().getData().toString());
        date = (EditText) findViewById(R.id.service_date);
        mileage = (EditText) findViewById(R.id.service_mileage);
        cost = (EditText) findViewById(R.id.service_cost);
        garage = (EditText) findViewById(R.id.service_garage);
        description = (EditText) findViewById(R.id.service_description);
        date.setInputType(InputType.TYPE_NULL);
        date.setText(DateUtils.dateToString(new Date()));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.dateBoxClicked(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_FORMAT_DEF));
                } catch (ParseException e) {
                    Logger.error(e);
                }
            }
        });

        maintenancesList = (NonScrollListView) findViewById(R.id.service_add_list);
        maintenancesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        maintenancesList.setAdapter(adapter);

        presenter = new AddServicePresenter(this, carId);

        setToolbarTitle(R.string.service_add_label);
        presenter.setList();

        achievementUtils = new AchievementUtils(this, findViewById(android.R.id.content));
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
                saveService();
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }


    private void saveService() {
        ServiceJobs service = new ServiceJobs();
        boolean error = false;

        //TODO Obsługa przekroczenia wartości zmiennych

        if (mileage.length() < 1) {
            error = true;
            mileage.setError(getString(R.string.error));
        } else if (mileage.length() > 9) {

            error = true;
            mileage.setError(getString(R.string.error_value));
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
                Date dateValue = DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_FORMAT_DEF);
                Date today = new Date();
                if (dateValue.after(today)) {
                    error = true;
                    date.setError(getString(R.string.error_value));
                } else {
                    date.setError(null);
                    service.setServiceDate(dateValue);
                }
            } catch (ParseException e) {
                Logger.error(e);
            }
            // !Date picker
        }

        service.setServiceGarage(garage.getText().toString());
        service.setServiceDescription(description.getText().toString());


        List<CarMaintenance> checked = adapter.getChecked(maintenancesList);


        if (!error) {
            presenter.saveService(service, checked);
            achievementUtils.checkService();
            finish();
        }
    }

    @Override
    public void setMaintanaceList(List<CarMaintenance> maintenances) {
        adapter.setItems(maintenances);

    }


    @Override
    public void showDataPicker(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setDate(date);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //TODO Pattern
                AddServiceActivity.this.date.setText(DateDialog.convertToString(year, monthOfYear, dayOfMonth));
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }

    @Override
    public void setCurrentData() {

    }
}
