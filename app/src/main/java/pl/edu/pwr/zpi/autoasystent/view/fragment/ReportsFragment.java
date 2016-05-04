package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.ReportsPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ReportsPanel;

/**
 * Created by Marek on 02.05.2016.
 */
public class ReportsFragment extends Fragment implements ReportsPanel, TabFragment {
    int duration = Toast.LENGTH_SHORT;
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
