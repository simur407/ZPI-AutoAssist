package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.view.RefuelingAddPanel;

/**
 * Created by Marcin on 25.04.2016.
 */
public class RefuelingAddPresenter {

    private RefuelingAddPanel panel;
    private long carId;

    public RefuelingAddPresenter(RefuelingAddPanel panel, long carId) {
        this.panel = panel;
        this.carId = carId;
    }

    public void saveRefueling(Refueling refueling) {
        refueling.setCar(CarService.getInstance().findCarById(carId));
        RefuelingService.getInstance().saveRefueling(refueling);
    }
}
