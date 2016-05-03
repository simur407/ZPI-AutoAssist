package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.CarAddServicePanel;

/**
 * Created by Szymon on 2016-04-02.
 */
public class AddServicePresenter {

    CarAddServicePanel panel;

    public AddServicePresenter(CarAddServicePanel panel) {
        this.panel = panel;
    }

    public void saveService(ServiceJobs service) {
        ServiceJobsService.getInstance().saveService(service);
    }

    public void dataBoxClicked(int currentData) {
        panel.showDataPicker(currentData);
    }


}
