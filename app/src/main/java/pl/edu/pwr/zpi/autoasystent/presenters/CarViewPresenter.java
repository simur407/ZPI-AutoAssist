package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.view.CarViewPanel;

/**
 * Created by Marcin on 25.04.2016.
 */
public class CarViewPresenter {

    protected CarViewPanel panel;

    public CarViewPresenter(CarViewPanel panel) {
        this.panel=panel;
    }

    public void setCarData(long carId) {
        panel.setCarData(Car.findById(Car.class, carId));
    }
}
