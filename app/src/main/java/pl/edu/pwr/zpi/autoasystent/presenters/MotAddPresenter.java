package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.MotService;
import pl.edu.pwr.zpi.autoasystent.view.MotAddPanel;


/**
 * Created by argo on 07 maj.
 */
public class MotAddPresenter {
    private MotAddPanel panel;
    private long carId;

    public MotAddPresenter(MotAddPanel panel, long carId) {
        this.panel = panel;
        this.carId = carId;
    }

    public void saveMot(Mot mot) {
        mot.setCar(CarService.getInstance().findCarById(carId));
        MotService.getInstance().saveMot(mot);
        panel.createReminder();
    }

    public void showDateDialog(Date date) {
        panel.showDateDialog(date);
    }
}
