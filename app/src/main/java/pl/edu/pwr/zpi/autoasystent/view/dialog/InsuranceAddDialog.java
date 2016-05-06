package pl.edu.pwr.zpi.autoasystent.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.presenters.InsuranceAddPresenter;
import pl.edu.pwr.zpi.autoasystent.view.InsuranceAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.fragment.TabFragment;

/**
 * Created by argo on 06 maj.
 */
public class InsuranceAddDialog extends CustomViewDialog implements InsuranceAddPanel, TabFragment {

    private EditText date, cost, description;
    protected InsuranceAddPresenter presenter;

    public InsuranceAddDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_insurance_add);

        presenter = new InsuranceAddPresenter(this);

        date = (EditText) findViewById(R.id.insurance_date);
        cost = (EditText) findViewById(R.id.insurance_cost);
        description = (EditText) findViewById(R.id.insurance_description);
        setTitle("Ubezpieczenia");
        setPositiveButton("Zapisz", new OnClickListener() {//TODO dać tu referencje do strings.xml
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveInsurance();
                Toast.makeText(InsuranceAddDialog.this.getContext(), "Zapisano!", Toast.LENGTH_SHORT).show();
            }
        });
        setNegativeButton("Anuluj", null);

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
