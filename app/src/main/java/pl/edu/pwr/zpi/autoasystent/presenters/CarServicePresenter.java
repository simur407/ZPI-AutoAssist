package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarServicePanel;

/**
 * Created by Szymon on 2016-04-02.
 */
public class CarServicePresenter {

    CarServicePanel panel;

    public CarServicePresenter(CarServicePanel panel) {
        this.panel = panel;
    }

    void saveService(CarService service) {
        //TODO Save to database
    }

    public void dataBoxClicked(int currentData) {
        panel.showDataPicker(currentData);
    }


}
