package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;

/**
 * Created by Marek on 24.04.2016.
 */
public class RefuelAdapter extends ArrayAdapter<Refueling> {

    Context context;

    public RefuelAdapter(Context context) {
        super(context, R.layout.item_refuel);
        this.context = context;
    }

    public RefuelAdapter(Context context, int resource, List<Refueling> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_refuel, parent, false);
            holder = new ViewHolder();
            holder.dateTextView = (TextView) convertView.findViewById(R.id.refuel_date);
            holder.quantityTextView = (TextView) convertView.findViewById(R.id.refuel_quantity);
            holder.costTextView = (TextView) convertView.findViewById(R.id.refuel_cost);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Refueling refueling = getItem(position);

        if (refueling.getRefuelingDate() != null) {
            holder.dateTextView.setText(DateUtils.dateToString(refueling.getRefuelingDate()));
        }
        holder.quantityTextView.setText(String.format("%s %s", String.valueOf(refueling.getQuantity()), context.getString(R.string.quantity_symbol)));
        holder.costTextView.setText(String.format("%s %s", ((Double) refueling.getRefuelingCost()).toString(), context.getString(R.string.currency_symbol)));


        return convertView;
    }

    public static class ViewHolder {
        public TextView dateTextView;
        public TextView quantityTextView;
        public TextView costTextView;
    }

    public void setItems(List<Refueling> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

}
