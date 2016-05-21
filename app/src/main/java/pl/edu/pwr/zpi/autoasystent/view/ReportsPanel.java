package pl.edu.pwr.zpi.autoasystent.view;

import android.net.Uri;

import java.util.Date;

/**
 * Created by Marek on 02.05.2016.
 */
public interface ReportsPanel {
    void startActivity(Class<?> clazz, Uri additionalData);

    void showFromDatePicker(Date date);

    void showToDatePicker(Date date);
}
