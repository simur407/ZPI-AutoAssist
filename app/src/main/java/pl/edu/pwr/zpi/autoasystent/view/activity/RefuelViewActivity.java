package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelViewPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.RefuelViewPanel;


/**
 * Created by Marek on 18.04.2016.
 */
public class RefuelViewActivity extends BaseActivity implements RefuelViewPanel {
    TextView date;
    TextView quantity;
    TextView cost;
    TextView costPerOne;
    TextView mileage;
    TextView description;
    RefuelViewPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RefuelViewPresenter(this);
        setContentView(R.layout.fragment_refuel_view);
        date = (TextView) findViewById(R.id.refuel_date_field);
        quantity = (TextView) findViewById(R.id.refuel_quantity_field);
        cost = (TextView) findViewById(R.id.refuel_cost_field);
        costPerOne = (TextView) findViewById(R.id.refuel_cost_per_one_field);
        mileage = (TextView) findViewById(R.id.refuel_mileage_field);
        description = (TextView) findViewById(R.id.refuel_description_field);
        presenter.setRefueling(Long.parseLong(getIntent().getData().toString()));

        setToolbarTitle(R.string.refuel_view_label);
    }


    @Override
    public void setRefuelingData(Refueling refueling) {
        date.setText(DateUtils.dateToString(refueling.getRefuelingDate()));
        quantity.setText(String.valueOf(refueling.getQuantity()) + " l");
        cost.setText(String.valueOf(refueling.getRefuelingCost()) + " zł");
        costPerOne.setText(String.valueOf((Math.round(100 * refueling.getRefuelingCost() / refueling.getQuantity())) / 100.0) + " zł/l");
        mileage.setText(String.valueOf(refueling.getRefuelingMileage()) + " km");
        description.setText(refueling.getRefuelingDescription());
    }
}
