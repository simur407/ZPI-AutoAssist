package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-12
 */
public class CarAdapter extends ArrayAdapter<Car> {

    Context context;

    public CarAdapter(Context context) {
        super(context, R.layout.item_car);
        this.context = context;
    }

    public CarAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_car, parent, false);
            holder = new ViewHolder();
            holder.makeTextView = (TextView) convertView.findViewById(R.id.car_model);
            holder.modelTextView = (TextView) convertView.findViewById(R.id.car_name);
            holder.carImageView = (ImageView) convertView.findViewById(R.id.car_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Car car = getItem(position);
        holder.modelTextView.setText(car.getModel().getModelName());
        holder.makeTextView.setText(car.getModel().getMake().getMakeName());
        holder.carImageView.setImageResource(R.drawable.car_ico);
        return convertView;
    }

    public static class ViewHolder {
        public TextView makeTextView;
        public TextView modelTextView;
        public ImageView carImageView;
    }

    public void setItems(List<Car> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }
}
