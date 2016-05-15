package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportSummaryPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportSummaryPanel;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportSummaryActivity extends BaseActivity implements ReportSummaryPanel {
    ReportSummaryPresenter presenter;

    TextView overallCost;
    TextView costPerMonth;
    TextView fuelAverage;
    TextView fuelOverall;
    TextView servicesOverall;
    TextView mileageOverall;
    TextView mileagePerMonth;
    TextView toInsurance;
    TextView toMot;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ReportSummaryPresenter(this);
        setContentView(R.layout.fragment_report_summary);
        overallCost = (TextView) findViewById(R.id.report_summary_cost_overall_field);
        costPerMonth = (TextView) findViewById(R.id.report_summary_cost_per_month_field);
        fuelAverage = (TextView) findViewById(R.id.report_summary_fuel_average_field);
        fuelOverall = (TextView) findViewById(R.id.report_summary_fuel_overall_field);
        servicesOverall = (TextView) findViewById(R.id.report_summary_services_cost_overall_field);
        mileageOverall = (TextView) findViewById(R.id.report_summary_mileage_overall_field);
        mileagePerMonth = (TextView) findViewById(R.id.report_summary_mileage_per_month_field);
        toInsurance = (TextView) findViewById(R.id.report_summary_to_insurance_field);
        toMot = (TextView) findViewById(R.id.report_summary_to_mot_field);
    }
}
