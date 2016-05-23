package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.CarAddServicePanel;

/**
 * Created by Szymon on 2016-04-02.
 */
public class AddServicePresenter {

    private CarAddServicePanel panel;
    private long carId;

    public AddServicePresenter(CarAddServicePanel panel, long carId) {
        this.panel = panel;
        this.carId = carId;
    }

    public void saveService(ServiceJobs service) {
        service.setCar(CarService.getInstance().findCarById(carId));
        ServiceJobsService.getInstance().saveService(service);
    }

    public void dateBoxClicked(Date date) {
        panel.showDataPicker(date);
    }


}
