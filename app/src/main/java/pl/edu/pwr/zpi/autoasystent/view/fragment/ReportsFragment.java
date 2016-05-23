package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportsPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.utils.StringUtils;
import pl.edu.pwr.zpi.autoasystent.view.ReportsPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

/**
 * Created by Marek on 02.05.2016.
 */
public class ReportsFragment extends Fragment implements ReportsPanel, TabFragment {
    private ReportsPresenter presenter;
    private TextView fromDate;
    private TextView toDate;
    private long carId;
    public static final String ID_KEY = "id";
    public static final String DATE_FROM = "from";
    public static final String DATE_TO = "to";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        carId = getArguments().getLong(CarActivity.ID_KEY);
        presenter = new ReportsPresenter(this, carId);

        fromDate = (TextView) view.findViewById(R.id.report_from_field);
        toDate = (TextView) view.findViewById(R.id.report_to_field);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.report_list);
        Button button = (Button) view.findViewById(R.id.report_make_button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                presenter.onRadioButtonClicked(checkedId);
            }
        });
        fromDate.setText(DateUtils.dateToString(new Date()));
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.showFromDatePicker(DateUtils.stringToDate(fromDate.getText().toString(), DateUtils.DATE_FORMAT_DEF));
                } catch (ParseException e) {
                    Logger.error(e);
                }
            }
        });

        toDate.setText(DateUtils.dateToString(new Date()));
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.showToDatePicker(DateUtils.stringToDate(toDate.getText().toString(), DateUtils.DATE_FORMAT_DEF));
                } catch (ParseException e) {
                    Logger.error(e);
                }
            }
        });

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                dates();
                presenter.onGenerateButtonClick(v, presenter.onRadioButtonClicked(radioGroup.getCheckedRadioButtonId()));
            }
        });

        return view;
    }

    public String getTabName(Context context) {
        return StringUtils.getStringFromId(context, R.string.reports_tab_name);
    }


    public void startActivity(Class<?> clazz, Bundle args) {
        Intent intent = new Intent(this.getContext(), clazz);
        if (args != null) {
            intent.putExtras(args);
        }
        startActivity(intent);
    }

    @Override
    public void showFromDatePicker(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setDate(date);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fromDate.setText(DateDialog.convertToString(year, monthOfYear, dayOfMonth));
            }
        });

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }

    @Override
    public void showToDatePicker(Date date) {
        DateDialog dialog = new DateDialog();
        dialog.setDate(date);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                toDate.setText(DateDialog.convertToString(year, monthOfYear, dayOfMonth));
            }
        });

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, null);
    }

    public void dates() {
        Date from;
        Date to;
        try {
            presenter.setFromDate(DateUtils.stringToDate(fromDate.getText().toString(), DateUtils.DATE_FORMAT_DEF));
            presenter.setToDate(DateUtils.stringToDate(toDate.getText().toString(), DateUtils.DATE_FORMAT_DEF));
        } catch (ParseException e) {
            Logger.error(e);
        }
    }
}
