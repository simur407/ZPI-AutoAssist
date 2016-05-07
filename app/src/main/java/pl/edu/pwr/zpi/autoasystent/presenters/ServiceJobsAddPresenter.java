package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.activity.ServiceActivity;

/**
 * Created by argo on 07 maj.
 */
public class ServiceJobsAddPresenter {
    ServiceActivity activity;

    public ServiceJobsAddPresenter(ServiceActivity activity) {
        this.activity=activity;
    }

    public void saveServiceJob(ServiceJobs refueling) {
        //TODO connect make and model
        ServiceJobsService.getInstance().saveService(refueling);
    }
}
