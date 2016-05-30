package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_service_done_jobs, parent, false);
            holder = new ViewHolder();
            holder.maintenanceTextView = (TextView) convertView.findViewById(R.id.maintenance_label);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CarMaintenance maintenance = getItem(position);

        holder.maintenanceTextView.setText(maintenance.getMaintenanceNamePol());


        return convertView;
    }


    public static class ViewHolder {
        public TextView maintenanceTextView;
    }


    public void setItems(List<CarMaintenance> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }
}
