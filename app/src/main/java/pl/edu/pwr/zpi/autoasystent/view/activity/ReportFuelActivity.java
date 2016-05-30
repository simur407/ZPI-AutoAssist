package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportFuelPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportFuelPanel;
import pl.edu.pwr.zpi.autoasystent.view.fragment.ReportsFragment;

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
    protected long carId;
    protected Date fromDate;
    protected Date toDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carId = getIntent().getLongExtra(ReportsFragment.ID_KEY, -1);
        fromDate = (Date) getIntent().getSerializableExtra(ReportsFragment.DATE_FROM);
        toDate = (Date) getIntent().getSerializableExtra(ReportsFragment.DATE_TO);
        presenter = new ReportFuelPresenter(this, carId, fromDate, toDate);
        setContentView(R.layout.fragment_report_fuel);
        fuelAverage = (TextView) findViewById(R.id.report_fuel_average_field);
        fuelPerDay = (TextView) findViewById(R.id.report_fuel_per_day_field);
        fuelPerMonth = (TextView) findViewById(R.id.report_fuel_per_month_field);
        fuelOverall = (TextView) findViewById(R.id.report_fuel_overall_field);
        fuelCostPerDay = (TextView) findViewById(R.id.report_fuel_cost_per_day_field);
        fuelCostPerMonth = (TextView) findViewById(R.id.report_fuel_cost_per_month_field);
        fuelCostOverall = (TextView) findViewById(R.id.report_fuel_cost_overall_field);
        presenter.setList();
    }

    @Override
    public void setReportData(List<Refueling> refuelings) {
        if (refuelings.size() > 0) {
            int daysCount = (int) (1 + ((toDate.getTime() - fromDate.getTime()) / 86400000));
            double fCosts = 0.0;
            int maxMileage = 1;
            int minMileage = refuelings.get(0).getRefuelingMileage();
            double quantityOverall = 0.00;
            for (Refueling r : refuelings) {
                fCosts += r.getRefuelingCost();
                quantityOverall += r.getQuantity();
                if (r.getRefuelingMileage() > maxMileage) {
                    maxMileage = r.getRefuelingMileage();
                }
            }
            if (maxMileage == minMileage) {
                fuelAverage.setText(getString(R.string.no_data_entered));
            } else {
                fuelAverage.setText(String.format("%.2f %s/100%s", quantityOverall / ((maxMileage - minMileage) / 100), getString(R.string.quantity_symbol), getString(R.string.mileage_symbol)));
            }
            fuelPerDay.setText(String.format("%.2f %s", quantityOverall / daysCount, getString(R.string.quantity_symbol)));
            fuelPerMonth.setText(String.format("%.2f %s", quantityOverall * 30 / daysCount, getString(R.string.quantity_symbol)));
            fuelOverall.setText(String.format("%s %s", String.valueOf(Math.round(quantityOverall)), getString(R.string.quantity_symbol)));
            fuelCostPerDay.setText(String.format("%.2f %s", fCosts / daysCount, getString(R.string.currency_symbol)));
            fuelCostPerMonth.setText(String.format("%.2f %s", 30 * fCosts / daysCount, getString(R.string.currency_symbol)));
            fuelCostOverall.setText(String.format("%.2f %s", fCosts, getString(R.string.currency_symbol)));
        } else {
            fuelAverage.setText(getString(R.string.no_data_entered));
            fuelPerDay.setText(getString(R.string.no_data_entered));
            fuelPerMonth.setText(getString(R.string.no_data_entered));
            fuelOverall.setText(getString(R.string.no_data_entered));
            fuelCostPerDay.setText(getString(R.string.no_data_entered));
            fuelCostPerMonth.setText(getString(R.string.no_data_entered));
            fuelCostOverall.setText(getString(R.string.no_data_entered));
        }

    }


}
