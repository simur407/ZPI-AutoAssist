package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.CarService;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarFuelPanel;

/**
 * Created by Szymon on 2016-04-03.
 */
public class CarFuelPresenter {

    CarFuelPanel panel;

    public CarFuelPresenter(CarFuelPanel panel) {
        this.panel = panel;
    }

    public void dataBoxClicked(int currentData) {
        panel.showDataPicker(currentData);
    }

    void saveService(CarService service) {
        //TODO Save to database
    }
}
