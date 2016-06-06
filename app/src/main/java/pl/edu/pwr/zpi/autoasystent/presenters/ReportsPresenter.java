package pl.edu.pwr.zpi.autoasystent.presenters;

import android.os.Bundle;
import android.view.View;

import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.view.ReportsPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.ReportFuelActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.ReportServicesActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.ReportSummaryActivity;
import pl.edu.pwr.zpi.autoasystent.view.fragment.ReportsFragment;

/**
 * Created by Marek on 02.05.2016.
 */
public class ReportsPresenter {
    ReportsPanel panel;
    long id;
    Date fromDate;
    Date toDate;

    public ReportsPresenter(ReportsPanel panel, long id) {
        this.panel = panel;
        this.id = id;
    }

    public int onRadioButtonClicked(int checkedId) {
        //   Bundle args;
        int idA = -1;
        switch (checkedId) {
            case R.id.report_radio_fuel:
                idA = 1;
                break;
            case R.id.report_radio_services:
                idA = 2;
                break;
            case R.id.report_radio_summary:
                idA = 3;
                break;
        }
        return idA;
    }

    public void onGenerateButtonClick(View v, int idA) {


        Bundle args = new Bundle();
        args.putLong(ReportsFragment.ID_KEY, id);
        args.putSerializable(ReportsFragment.DATE_FROM, fromDate);
        args.putSerializable(ReportsFragment.DATE_TO, toDate);

        switch (idA) {
            case 1:
                panel.startActivity(ReportFuelActivity.class, args);
                break;
            case 2:
                panel.startActivity(ReportServicesActivity.class, args);
                break;
            case 3:
                panel.startActivity(ReportSummaryActivity.class, args);
                break;
        }

    }

    public void showFromDatePicker(Date date) {
        panel.showFromDatePicker(date);
    }

    public void showToDatePicker(Date date) {
        panel.showToDatePicker(date);
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
