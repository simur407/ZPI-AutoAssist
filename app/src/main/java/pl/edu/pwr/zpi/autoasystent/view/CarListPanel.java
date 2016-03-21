package pl.edu.pwr.zpi.autoasystent.view;

import android.net.Uri;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;

/**
 * Created by Szymon on 2016-03-21.
 */
public interface CarListPanel {
    void setCarList(List<Car> carList);
    void startActivity(Class<?> clazz, Uri additionalData);
    void refreshList();

}
