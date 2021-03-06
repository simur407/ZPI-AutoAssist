package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;

/**
 * Created by Marek on 25.04.2016.
 */
public class ServiceJobsAdapter extends ArrayAdapter<ServiceJobs> {

    Context context;

    public ServiceJobsAdapter(Context context) {
        super(context, R.layout.item_service);
        this.context = context;
    }

    public ServiceJobsAdapter(Context context, int resource, List<ServiceJobs> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_service, parent, false);
            holder = new ViewHolder();
            holder.dateTextView = (TextView) convertView.findViewById(R.id.service_date);
            holder.costTextView = (TextView) convertView.findViewById(R.id.service_cost);
            holder.mileageTextView = (TextView) convertView.findViewById(R.id.service_mileage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ServiceJobs serviceJobs = getItem(position);

        if (serviceJobs.getServiceDate() != null) {
            holder.dateTextView.setText(DateUtils.dateToString(serviceJobs.getServiceDate()));
        }
        holder.costTextView.setText(String.format("%.2f %s", serviceJobs.getServiceCost(), context.getString(R.string.currency_symbol)));
        holder.mileageTextView.setText(String.format("%s %s", String.valueOf(serviceJobs.getServiceMileage()), context.getString(R.string.mileage_symbol)));

        return convertView;
    }

    public static class ViewHolder {
        public TextView dateTextView;
        public TextView costTextView;
        public TextView mileageTextView;
    }

    public void setItems(List<ServiceJobs> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

}
