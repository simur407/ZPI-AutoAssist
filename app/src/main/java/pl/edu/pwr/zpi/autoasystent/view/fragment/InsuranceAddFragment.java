package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.presenters.InsuranceAddPresenter;
import pl.edu.pwr.zpi.autoasystent.view.InsuranceAddPanel;

/**
 * Created by argo on 06 maj.
 */
public class InsuranceAddFragment extends Fragment implements InsuranceAddPanel, TabFragment {

    private EditText date, cost, description;
    protected InsuranceAddPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_insurance_add, container, false);

        presenter = new InsuranceAddPresenter(this);

        date = (EditText) view.findViewById(R.id.insurance_date);
        cost = (EditText) view.findViewById(R.id.insurance_cost);
        description = (EditText) view.findViewById(R.id.insurance_description);

        return view;
    }

    private void saveInsurance() {
        Insurance insurance = new Insurance();

//        insurance.setInsuranceDate(Integer.parseInt(date.getText().toString()));
        insurance.setInsuranceCost(Double.parseDouble(cost.getText().toString()));
        insurance.setInsuranceDescription(description.getText().toString());

        presenter.saveInsurance(insurance);
    }

    public String getTabName() {
        return "Ubezpieczenia";
    }
}
