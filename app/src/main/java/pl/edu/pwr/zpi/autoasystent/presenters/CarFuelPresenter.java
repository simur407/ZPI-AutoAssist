package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.view.CarFuelPanel;

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

    void saveService(CarMaintenance service) {
        //TODO Save to database
    }
}
