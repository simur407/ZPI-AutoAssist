package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportSummaryPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportSummaryPanel;
import pl.edu.pwr.zpi.autoasystent.view.fragment.ReportsFragment;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportSummaryActivity extends BaseActivity implements ReportSummaryPanel {
    ReportSummaryPresenter presenter;

    TextView overallCost;
    TextView costPerMonth;
    TextView fuelAverage;
    TextView fuelOverall;
    TextView fuelOverallCost;
    TextView servicesOverall;
    TextView mileageOverall;
    TextView mileagePerMonth;
    TextView toInsurance;
    TextView toMot;
    protected long carId;
    protected Date fromDate;
    protected Date toDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carId = getIntent().getLongExtra(ReportsFragment.ID_KEY, -1);
        fromDate = (Date) getIntent().getSerializableExtra(ReportsFragment.DATE_FROM);
        toDate = (Date) getIntent().getSerializableExtra(ReportsFragment.DATE_TO);
        presenter = new ReportSummaryPresenter(this, carId, fromDate, toDate);
        setContentView(R.layout.fragment_report_summary);
        overallCost = (TextView) findViewById(R.id.report_summary_cost_overall_field);
        costPerMonth = (TextView) findViewById(R.id.report_summary_cost_per_month_field);
        fuelAverage = (TextView) findViewById(R.id.report_summary_fuel_average_field);
        fuelOverall = (TextView) findViewById(R.id.report_summary_fuel_overall_field);
        fuelOverallCost = (TextView) findViewById(R.id.report_summary_fuel_cost_overall_field);
        servicesOverall = (TextView) findViewById(R.id.report_summary_services_cost_overall_field);
        mileageOverall = (TextView) findViewById(R.id.report_summary_mileage_overall_field);
        mileagePerMonth = (TextView) findViewById(R.id.report_summary_mileage_per_month_field);
        toInsurance = (TextView) findViewById(R.id.report_summary_to_insurance_field);
        toMot = (TextView) findViewById(R.id.report_summary_to_mot_field);
        presenter.setList();
    }

    @Override
    public void setReportData(List<Refueling> refuelings, List<ServiceJobs> serviceJobses, List<Mot> mots, List<Insurance> insurances, Car car) {
        int maxMileage = car.getStartMileage();
        int minFuelMileage = 1;
        int maxFuelMileage = 1;

        int minMileage = car.getStartMileage();
        String sAverageFuelConsumption = getString(R.string.no_data_entered);
        double averageFuelConsumption = 0.00;
        long daysCount = 1 + ((toDate.getTime() - fromDate.getTime()) / 86400000);
        int timeToMot = 0;
        String sTimeToMot = getString(R.string.expired);
        if (mots.size() > 0) {
            timeToMot = (int) ((mots.get(mots.size() - 1).getMotDate().getTime() - new Date().getTime()) / 86400000);
        } else {
            sTimeToMot = getString(R.string.no_data_entered);
        }

        if (timeToMot > 0) {
            sTimeToMot = Integer.toString(timeToMot);
        }
        int timeToInsurance = 0;
        String sTimetoInsurance = getString(R.string.expired);
        if (insurances.size() > 0) {
            timeToInsurance = (int) ((insurances.get(insurances.size() - 1).getInsuranceDate().getTime() - new Date().getTime()) / 86400000);
        } else {
            sTimetoInsurance = getString(R.string.no_data_entered);
        }
        if (timeToInsurance > 0) {
            sTimetoInsurance = Integer.toString(timeToInsurance);
        }

        double fCosts = 0.00;
        double fuelConsumed = 0.00;
        if (refuelings.size() > 0) {
            maxFuelMileage = refuelings.get(refuelings.size() - 1).getRefuelingMileage();
            minFuelMileage = refuelings.get(0).getRefuelingMileage();

            if (maxFuelMileage > maxMileage) {
                maxMileage = maxFuelMileage;
            }
            minMileage = minFuelMileage;
        }

        for (Refueling r : refuelings) {
            fCosts += r.getRefuelingCost();
            fuelConsumed += r.getQuantity();
        }

        double sCosts = 0.0;
        for (ServiceJobs s : serviceJobses) {
            if (s.getServiceMileage() > maxMileage) {
                maxMileage = s.getServiceMileage();
            }
            if (s.getServiceMileage() < minMileage) {
                minMileage = s.getServiceMileage();
            }
            sCosts += s.getServiceCost();
        }
        double iCosts = 0;
        for (Insurance i : insurances) {
            if (!insurances.isEmpty() && fromDate.before(i.getInsuranceDate()) && toDate.after(i.getInsuranceDate())) {
                iCosts += i.getInsuranceCost();
            }
        }
        double oCosts = fCosts + sCosts + iCosts;
        double oCostMonth = oCosts;
        int mPerMonth = (maxMileage - minMileage);
        if (daysCount > 25) {
            oCostMonth = oCosts * 30 / daysCount;
            mPerMonth = mPerMonth / (int) daysCount * 30;
        }

        if (refuelings.size() > 1) {
            averageFuelConsumption = (fuelConsumed - refuelings.get(0).getQuantity()) / ((double) (maxFuelMileage - minFuelMileage) / 100);
            sAverageFuelConsumption = String.format("%.2f %s/100%s", averageFuelConsumption, getString(R.string.quantity_symbol), getString(R.string.mileage_symbol));
        }

        overallCost.setText(String.format("%.2f %s", oCosts, getString(R.string.currency_symbol)));
        costPerMonth.setText(String.format("%.2f %s", oCostMonth, getString(R.string.currency_symbol)));
        fuelAverage.setText(sAverageFuelConsumption);
        fuelOverall.setText(String.format("%s %s", String.valueOf(Math.round(fuelConsumed)), getString(R.string.quantity_symbol)));
        fuelOverallCost.setText(String.format("%.2f %s", fCosts, getString(R.string.currency_symbol)));
        servicesOverall.setText(String.format("%.2f %s", sCosts, getString(R.string.currency_symbol)));
        mileageOverall.setText(String.format("%s %s", String.valueOf(maxMileage - minMileage), getString(R.string.mileage_symbol)));
        mileagePerMonth.setText(String.format("%s %s", String.valueOf(mPerMonth), getString(R.string.mileage_symbol)));

        toMot.setText(sTimeToMot);
        toInsurance.setText(sTimetoInsurance);

    }
}
