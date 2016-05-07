package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.CarServicePresenter;
import pl.edu.pwr.zpi.autoasystent.presenters.ServiceJobsAddPresenter;
import pl.edu.pwr.zpi.autoasystent.view.CarServiceAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

public class ServiceActivity extends BaseActivity implements CarServiceAddPanel{

    protected EditText date, mileage, cost, garage, description;
    protected ListView list;

    protected ServiceJobsAddPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);

        date = (EditText) findViewById(R.id.service_date);
        mileage = (EditText) findViewById(R.id.service_mileage);
        cost = (EditText) findViewById(R.id.service_cost);
        garage = (EditText) findViewById(R.id.service_garage);
        description = (EditText) findViewById(R.id.service_description);

        date.setInputType(InputType.TYPE_NULL);

        list = (ListView) findViewById(R.id.service_add_list);

        presenter = new ServiceJobsAddPresenter(this);
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
//        RefersTo refersTo = new RefersTo();

        service.setServiceMileage(Integer.parseInt(mileage.getText().toString()));
        service.setServiceCost(Double.parseDouble(cost.getText().toString()));
        service.setServiceGarage(garage.getText().toString());
        service.setServiceDescription(description.getText().toString());

        String dateString = date.getText().toString();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = df.parse(dateString);
            service.setServiceDate(date);
            presenter.saveServiceJob(service);
            finish();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        ArrayList<String> serviceList = new ArrayList<String>();
//
//        for(String element : serviceList)
//            refersTo.getMaintenance().setMaintenanceName(element.toString());
//        }

        finish();
    }

    public void onStart(){
        super.onStart();

        EditText txtDate=(EditText)findViewById(R.id.service_date);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    dialog.show(ft, "Wybierz datę");
                }
            }

        });
    }
}
