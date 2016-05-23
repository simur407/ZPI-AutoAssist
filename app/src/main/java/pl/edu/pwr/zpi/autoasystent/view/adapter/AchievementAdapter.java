package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import pl.edu.pwr.zpi.autoasystent.model.Achievement;

/**
 * Created by Marek on 16.05.2016.
 */
public class AchievementAdapter extends BaseAdapter {
    private Context mContext;

    public AchievementAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return Achievement.values().length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //  imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //   imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(Achievement.values()[position].getDrawable());
        return imageView;
    }
}
