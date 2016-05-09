package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
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

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportsPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportsPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

/**
 * Created by Marek on 02.05.2016.
 */
public class ReportsFragment extends Fragment implements ReportsPanel, TabFragment {
    private ReportsPresenter presenter;
    private TextView fromDate;
    private TextView toDate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        presenter = new ReportsPresenter(this);

        fromDate = (TextView) view.findViewById(R.id.report_from_field);
        toDate = (TextView) view.findViewById(R.id.report_to_field);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.report_list);
        Button button = (Button) view.findViewById(R.id.report_make_button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                presenter.onRadioButtonClicked(checkedId);
            }
        });

        fromDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    DateDialog dialog = new DateDialog();
                    dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            fromDate.setText(dayOfMonth+"." +monthOfYear + "." +year);
                        }
                    });

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, null);
                }
            }

        });

        toDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    //TODO przez prezenter
                    DateDialog dialog = new DateDialog();
                    dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            toDate.setText(dayOfMonth+"." +monthOfYear + "." +year);
                        }
                    });

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, null);
                }
            }

        });

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                presenter.onGenerateButtonClick(v);
            }
        });

        return view;
    }


    public String getTabName() {
        return "Raporty";
    }


    public void startActivity(Class<?> clazz, Uri additionalData) {
        Intent intent = new Intent(this.getContext(), clazz);
        if (additionalData != null) {
            intent.setData(additionalData);
        }
        startActivity(intent);
    }
}
