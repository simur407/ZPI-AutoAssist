package pl.edu.pwr.zpi.autoasystent.presenters;


import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.InsuranceService;
import pl.edu.pwr.zpi.autoasystent.view.InsuranceAddPanel;

/**
 * Created by Marek on 09.05.2016.
 */
public class InsuranceAddPresenter {
    private InsuranceAddPanel panel;
    private long carId;

    public InsuranceAddPresenter(InsuranceAddPanel panel, long carId) {
        this.panel = panel;
        this.carId = carId;
    }

    public void saveInsurance(Insurance insurance) {
        insurance.setCar(CarService.getInstance().findCarById(carId));
        InsuranceService.getInstance().saveInsurance(insurance);
        panel.createReminder();
    }

    public void showDatePicker(Date date) {
        panel.showDatePicker(date);
    }
}
