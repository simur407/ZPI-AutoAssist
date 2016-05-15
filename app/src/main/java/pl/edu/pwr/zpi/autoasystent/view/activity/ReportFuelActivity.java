package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportFuelPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportFuelPanel;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportFuelActivity extends BaseActivity implements ReportFuelPanel {
    TextView fuelAverage;
    TextView fuelPerDay;
    TextView fuelPerMonth;
    TextView fuelOverall;
    TextView fuelCostPerDay;
    TextView fuelCostPerMonth;
    TextView fuelCostOverall;
    ReportFuelPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ReportFuelPresenter(this);
        setContentView(R.layout.fragment_report_fuel);
        fuelAverage = (TextView) findViewById(R.id.report_fuel_average_field);
        fuelPerDay = (TextView) findViewById(R.id.report_fuel_per_day_field);
        fuelPerMonth = (TextView) findViewById(R.id.report_fuel_per_month_field);
        fuelOverall = (TextView) findViewById(R.id.report_fuel_overall_field);
        fuelCostPerDay = (TextView) findViewById(R.id.report_fuel_cost_per_day_field);
        fuelCostPerMonth = (TextView) findViewById(R.id.report_fuel_cost_per_month_field);
        fuelCostOverall = (TextView) findViewById(R.id.report_fuel_cost_overall_field);
    }


}
