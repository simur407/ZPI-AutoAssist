package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.RefersTo;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.RefersToService;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.ServiceViewPanel;

/**
 * Created by Marek on 25.04.2016.
 */
public class ServiceJobsViewPresenter {
    ServiceViewPanel panel;

    public ServiceJobsViewPresenter(ServiceViewPanel panel) {
        this.panel = panel;
    }

    public void setServiceJob(long id) {
        ServiceJobs service = ServiceJobsService.getInstance().findServiceById(id);
        List<RefersTo> refersTos = RefersToService.getInstance().getRefersToByServiceId(id);
        panel.setServiceJob(service);
        panel.setMaintenances(refersTos);
    }


}
