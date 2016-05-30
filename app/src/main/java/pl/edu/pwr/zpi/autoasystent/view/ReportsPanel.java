package pl.edu.pwr.zpi.autoasystent.view;

import android.os.Bundle;

import java.util.Date;

/**
 * Created by Marek on 02.05.2016.
 */
public interface ReportsPanel {
    void startActivity(Class<?> clazz, Bundle args);

    void showFromDatePicker(Date date);

    void showToDatePicker(Date date);
}
