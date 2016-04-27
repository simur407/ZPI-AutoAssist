package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

/**
 * Created by argo on 11 kwi.
 */
public class RefersTo extends SugarRecord {

    private ServiceJobs service;

    private CarMaintenance maintenance;

    public RefersTo() {
    }

    public RefersTo(ServiceJobs service, CarMaintenance maintenance) {
        this.service = service;
        this.maintenance = maintenance;
    }
}
