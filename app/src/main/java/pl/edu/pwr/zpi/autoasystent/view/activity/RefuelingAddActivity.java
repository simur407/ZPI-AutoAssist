package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelingAddPresenter;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

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
        carId=1;
        dateField=(TextView)findViewById(R.id.refuel_date);
        mileageField=(TextView)findViewById(R.id.refuel_mileage);
        costField=(TextView)findViewById(R.id.refuel_cost);
        quantityField=(TextView)findViewById(R.id.refuel_quantity);
        descriptionField=(TextView)findViewById(R.id.refuel_description);

        presenter = new RefuelingAddPresenter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save: saveRefueling();
            default: super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void saveRefueling()
    {
        Refueling refueling=new Refueling();
        refueling.setCar(CarService.getInstance().findCarById(carId));
        refueling.setQuantity(Integer.parseInt(quantityField.getText().toString()));
        refueling.setRefuelingCost(Double.parseDouble(costField.getText().toString()));
        refueling.setRefuelingMileage(Integer.parseInt(mileageField.getText().toString()));
        refueling.setRefuelingDescription(descriptionField.getText().toString());

        String dateString=dateField.getText().toString();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = df.parse(dateString);
            refueling.setRefuelingDate(date);
            presenter.saveRefueling(refueling);
            finish();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void onStart(){
        super.onStart();

        EditText txtDate=(EditText)findViewById(R.id.refuel_date);
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
