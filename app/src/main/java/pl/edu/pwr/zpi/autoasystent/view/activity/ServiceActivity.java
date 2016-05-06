package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.view.CarServiceAddPanel;

public class ServiceActivity extends BaseActivity implements CarServiceAddPanel{

    private EditText date, mileage, cost, garage, description;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);

        date = (EditText) findViewById(R.id.service_date);
        mileage = (EditText) findViewById(R.id.service_mileage);
        cost = (EditText) findViewById(R.id.service_cost);
        garage = (EditText) findViewById(R.id.service_garage);
        description = (EditText) findViewById(R.id.service_description);

        list = (ListView) findViewById(R.id.service_add_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO obsluzyc zapis
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
//TODO
//        service.setServiceDate(date.getText()));
        //service.setServiceDate(Calendar.getInstance().getTime());
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
        service.setServiceGarage(garage.getText().toString());
        service.setServiceDescription(description.getText().toString());

//        ArrayList<String> serviceList = new ArrayList<String>();
//
//        for(String element : serviceList)
//            refersTo.getMaintenance().setMaintenanceName(element.toString());
//        }
        if (!error) {
        finish();
        }
    }
}
