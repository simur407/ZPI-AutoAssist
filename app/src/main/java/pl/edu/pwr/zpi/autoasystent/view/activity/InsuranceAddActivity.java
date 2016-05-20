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

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.presenters.InsuranceAddPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.InsuranceAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

/**
 * Created by Marek on 09.05.2016.
 */
public class InsuranceAddActivity extends BaseActivity implements InsuranceAddPanel {
    private EditText date, cost, description;
    protected InsuranceAddPresenter presenter;
    protected long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carId = Long.valueOf(getIntent().getData().toString());
        setContentView(R.layout.fragment_insurance_add);

        presenter = new InsuranceAddPresenter(this, carId);

        date = (EditText) findViewById(R.id.insurance_date);
        cost = (EditText) findViewById(R.id.insurance_cost);
        description = (EditText) findViewById(R.id.insurance_description);

        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDatePicker(new Date());//TODO temporary date
            }
        });
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
                saveInsurance();
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }


    private void saveInsurance() {
        Insurance insurance = new Insurance();
        boolean error = false;

//        insurance.setInsuranceDate(Integer.parseInt(date.getText().toString()));
        if (cost.length() < 1) {
            error = true;
            cost.setError("Pole obowiązkowe");
        } else {
            insurance.setInsuranceCost(Double.parseDouble(cost.getText().toString()));
        }
        insurance.setInsuranceDescription(description.getText().toString());
        try {
            insurance.setInsuranceDate(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_FORMAT_DEF));
        } catch (ParseException e) {
            Logger.error(e);
        }
        if (!error) {
            presenter.saveInsurance(insurance);
            finish();
        }
    }

    @Override
    public void showDatePicker(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //TODO Pattern
                InsuranceAddActivity.this.date.setText(dayOfMonth+"." +monthOfYear + "." +year);
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }
}
