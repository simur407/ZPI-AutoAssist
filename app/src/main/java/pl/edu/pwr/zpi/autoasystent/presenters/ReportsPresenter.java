package pl.edu.pwr.zpi.autoasystent.presenters;

import android.view.View;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.view.ReportsPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.ReportFuelActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.ReportServicesActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.ReportSummaryActivity;

/**
 * Created by Marek on 02.05.2016.
 */
public class ReportsPresenter {
    ReportsPanel panel;

    public ReportsPresenter(ReportsPanel panel) {
        this.panel = panel;
    }

    public void onRadioButtonClicked(int checkedId) {

        switch (checkedId) {
            case R.id.report_radio_fuel:
                //TODO implement
                panel.startActivity(ReportFuelActivity.class, null);
                break;
            case R.id.report_radio_services:
                panel.startActivity(ReportServicesActivity.class, null);
                // TODO implement
                break;
            case R.id.report_radio_summary:
                panel.startActivity(ReportSummaryActivity.class, null);
                // TODO implement
                break;
            case R.id.report_radio_fuel_chart:
                //  if (checked)
                // TODO implement
                break;
            case R.id.report_radio_summary_chart:
                //  if (checked)
                // TODO implement
                break;
        }
    }

    public void onGenerateButtonClick(View v) {
        //TODO implement
    }
}
