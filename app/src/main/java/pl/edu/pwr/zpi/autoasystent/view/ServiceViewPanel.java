package pl.edu.pwr.zpi.autoasystent.view;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.RefersTo;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by Marek on 25.04.2016.
 */
public interface ServiceViewPanel {
    void setServiceJob(ServiceJobs serviceJob);

    void setMaintenances(List<RefersTo> refersTos);
}
