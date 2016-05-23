package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportServicesPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportServicesPanel;
import pl.edu.pwr.zpi.autoasystent.view.fragment.ReportsFragment;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportServicesActivity extends BaseActivity implements ReportServicesPanel {
    ReportServicesPresenter presenter;
    TextView servicesCount;
    TextView servicesCostOverall;
    TextView servicesCostAverage;
    TextView servicesCostPerMonth;
    protected long carId;
    protected Date fromDate;
    protected Date toDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carId = getIntent().getLongExtra(ReportsFragment.ID_KEY, -1);
        fromDate = (Date) getIntent().getSerializableExtra(ReportsFragment.DATE_FROM);
        toDate = (Date) getIntent().getSerializableExtra(ReportsFragment.DATE_TO);
        presenter = new ReportServicesPresenter(this, carId, fromDate, toDate);
        setContentView(R.layout.fragment_report_services);
        servicesCount = (TextView) findViewById(R.id.report_services_count_field);
        servicesCostOverall = (TextView) findViewById(R.id.report_services_cost_overall_field);
        servicesCostAverage = (TextView) findViewById(R.id.report_services_cost_average_field);
        servicesCostPerMonth = (TextView) findViewById(R.id.report_services_cost_per_month_field);
        presenter.setList();

    }

    public void setReportData(List<ServiceJobs> serviceJobses) {
        if (serviceJobses.size() > 0) {
            double oCost = 0;
            long daysCount = (toDate.getTime() - fromDate.getTime()) / 86400000;
            for (ServiceJobs s : serviceJobses) {
                oCost += s.getServiceCost();
            }
            servicesCount.setText(String.valueOf(serviceJobses.size()));
            servicesCostOverall.setText(String.format("%s %s", String.valueOf((int) oCost), getString(R.string.currency_symbol)));
            servicesCostAverage.setText(String.format("%s %s", String.valueOf((int) oCost / serviceJobses.size()), getString(R.string.currency_symbol)));
            servicesCostPerMonth.setText(String.format("%s %s", String.valueOf(Math.round(oCost / daysCount * 30)), getString(R.string.currency_symbol)));
        } else {
            servicesCount.setText(getString(R.string.no_data_entered));
            servicesCostOverall.setText(getString(R.string.no_data_entered));
            servicesCostAverage.setText(getString(R.string.no_data_entered));
            servicesCostPerMonth.setText(getString(R.string.no_data_entered));
        }
    }
}
