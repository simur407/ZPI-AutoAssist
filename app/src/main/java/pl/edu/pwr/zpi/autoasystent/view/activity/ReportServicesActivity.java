package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportServicesPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportServicesPanel;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportServicesActivity extends BaseActivity implements ReportServicesPanel {
    ReportServicesPresenter presenter;
    TextView servicesCount;
    TextView servicesCostOverall;
    TextView servicesCostAverage;
    TextView servicesCostPerMonth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ReportServicesPresenter(this);
        setContentView(R.layout.fragment_report_services);
        servicesCount = (TextView) findViewById(R.id.report_services_count_field);
        servicesCostOverall = (TextView) findViewById(R.id.report_services_cost_overall_field);
        servicesCostAverage = (TextView) findViewById(R.id.report_services_cost_average_field);
        servicesCostPerMonth = (TextView) findViewById(R.id.report_services_cost_per_month_field);

    }


}
