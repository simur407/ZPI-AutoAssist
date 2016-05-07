package pl.edu.pwr.zpi.autoasystent.presenters;

import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import com.rafalzajfert.androidlogger.Logger;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarListPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarAddActivity;

/**
 * Created by Szymon on 2016-03-21.
 */
public class CarListPresenter {

    CarListPanel panel;

    public CarListPresenter(CarListPanel panel) {
        this.panel = panel;
    }

    public void setList() {
        List<Car> cars = CarService.getInstance().getAllCars();
        panel.setCarList(cars);
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        Car car = (Car)parent.getItemAtPosition(position);
        Logger.debug(car);
        panel.startActivity(CarActivity.class, Uri.parse(String.valueOf(car.getId())));
    }

    public void onAddButtonClick(View v) {
        panel.startActivity(CarAddActivity.class, null);
    }
}
