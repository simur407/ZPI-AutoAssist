package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.ServiceJobsViewPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.ServiceViewPanel;

/**
 * Created by Marek on 25.04.2016.
 */
public class ServiceJobsActivity extends BaseActivity implements ServiceViewPanel {
    TextView date;
    TextView cost;
    TextView mileage;
    TextView garage;
    TextView description;
    TextView servicesDone;
    ServiceJobsViewPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ServiceJobsViewPresenter(this);
        setContentView(R.layout.fragment_service_view);
        date = (TextView) findViewById(R.id.service_date_field);
        cost = (TextView) findViewById(R.id.service_cost_field);
        mileage = (TextView) findViewById(R.id.service_mileage_field);
        garage = (TextView) findViewById(R.id.garage_field);
        description = (TextView) findViewById(R.id.service_description_field);
        //TODO servicesDone
        presenter.setServiceJob(Long.parseLong(getIntent().getData().toString()));
    }

    public void setServiceJob(ServiceJobs serviceJobs) {
        date.setText(DateUtils.dateToString(serviceJobs.getServiceDate()));
        cost.setText(String.format("%s %s", String.valueOf(serviceJobs.getServiceCost()), getString(R.string.currency_symbol)));
        mileage.setText(String.format("%s %s", String.valueOf(serviceJobs.getServiceMileage()), getString(R.string.mileage_symbol)));
        if (serviceJobs.getGarage().length() < 1) {
            garage.setText("-");
        } else {
            garage.setText(serviceJobs.getGarage());
        }
        if (serviceJobs.getServiceDescription().length() < 1) {
            description.setText("-");
        } else {
            description.setText(serviceJobs.getServiceDescription());
        }
    }
}
