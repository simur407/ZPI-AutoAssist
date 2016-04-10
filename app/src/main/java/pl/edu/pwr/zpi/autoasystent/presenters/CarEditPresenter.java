package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarEditPanel;

/**
 * Created by Szymon on 2016-03-21.
 */
public class CarEditPresenter extends CarModifyPresenter{

    public CarEditPresenter(CarEditPanel panel) {
        super(panel);
    }

    public void setInitialData(long carId) {
        Car car = CarService.getInstance().findCarById(carId);
        ((CarEditPanel)panel).setInitialData(car);
    }
}
