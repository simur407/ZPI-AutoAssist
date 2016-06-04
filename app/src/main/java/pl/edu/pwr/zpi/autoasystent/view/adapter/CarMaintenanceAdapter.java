package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.utils.NonScrollListView;

/**
 * Created by Marek on 30.05.2016.
 */
public class CarMaintenanceAdapter extends ArrayAdapter<CarMaintenance> {
    Context context;
    boolean[] itemChecked;
    int listSize;

    public CarMaintenanceAdapter(Context context) {

        super(context, R.layout.item_service_done_jobs);
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_service_done_jobs, parent, false);
            holder = new ViewHolder();
            holder.maintenanceTextView = (TextView) convertView.findViewById(R.id.maintenance_label);
            holder.maintenanceCheckBox = (CheckBox) convertView.findViewById(R.id.maintenance_checkbox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.maintenanceCheckBox.getTag(position);
        }
        holder.maintenanceCheckBox.setTag(position);

        CarMaintenance maintenance = getItem(position);

        //TODO languages
        holder.maintenanceTextView.setText(maintenance.getMaintenanceNamePol());
        // holder.maintenanceTextView.setText(maintenance.getMaintenanceNameEng());
        // holder.maintenanceTextView.setText(maintenance.getMaintenanceNameDeu());


        holder.maintenanceCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.maintenanceCheckBox.isChecked()) {
                    itemChecked[position] = true;
                } else {
                    itemChecked[position] = false;
                }
            }
        });


        return convertView;
    }


    public static class ViewHolder {
        public TextView maintenanceTextView;
        public CheckBox maintenanceCheckBox;
    }


    public void setItems(List<CarMaintenance> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
        listSize = items.size();
        itemChecked = new boolean[listSize];
    }

    public List<CarMaintenance> getChecked(NonScrollListView listView) {
        ArrayList<CarMaintenance> checkedList = new ArrayList<>();

        for (int i = 0; i < listView.getAdapter().getCount(); i++) {
            if (itemChecked[i]) {
                checkedList.add((CarMaintenance) listView.getItemAtPosition(i));
            }
        }
        return checkedList;
    }


}
