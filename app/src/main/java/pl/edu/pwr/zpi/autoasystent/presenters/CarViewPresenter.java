package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.view.CarViewPanel;

/**
 * Created by Marcin on 25.04.2016.
 */
public class CarViewPresenter {

    protected CarViewPanel panel;

    public CarViewPresenter(CarViewPanel panel) {
        this.panel=panel;
    }

    public void setCarData(int carId) {
        panel.setCarData(Car.findById(Car.class, carId));
    }
}
