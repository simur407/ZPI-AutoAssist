package pl.edu.pwr.zpi.autoasystent.view;

import android.net.Uri;

import pl.edu.pwr.zpi.autoasystent.model.Car;

/**
 * Created by Marcin on 25.04.2016.
 */
public interface CarViewPanel {
    public void setCarData(Car car);

    void startActivity(Class<?> clazz, Uri additionalData);
}
