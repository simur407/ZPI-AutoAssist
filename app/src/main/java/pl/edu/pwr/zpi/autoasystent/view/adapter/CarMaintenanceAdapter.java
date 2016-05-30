package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;

/**
 * Created by Marek on 30.05.2016.
 */
public class CarMaintenanceAdapter extends ArrayAdapter<CarMaintenance> {
    Context context;

    public CarMaintenanceAdapter(Context context) {

        super(context, R.layout.item_service_done_jobs);
        this.context = context;
    }
}
