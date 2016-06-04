package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.model.RefersTo;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.CarMaintenanceService;
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

    public void saveService(ServiceJobs service, List<CarMaintenance> chcecked) {
        service.setCar(CarService.getInstance().findCarById(carId));
        ServiceJobsService.getInstance().saveService(service);
        saveMaintenances(chcecked, service);
    }

    public void dateBoxClicked(Date date) {
        panel.showDataPicker(date);
    }


    public void setList() {
        List<CarMaintenance> maintenances = CarMaintenanceService.getInstance().getMaintenancesList();
        panel.setMaintanaceList(maintenances);
    }

    public void saveMaintenances(List<CarMaintenance> checked, ServiceJobs service) {

        for (CarMaintenance m : checked) {
            RefersTo rT = new RefersTo(service, m);
            rT.save();
        }
    }


}
