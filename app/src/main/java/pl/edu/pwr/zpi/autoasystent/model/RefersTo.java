package pl.edu.pwr.zpi.autoasystent.model;

/**
 * Created by argo on 11 kwi.
 */
public class RefersTo {

    private ServiceJobs service;

    private CarMaintenance maintenance;

    public RefersTo() {

    }

    public ServiceJobs getService() {
        return service;
    }

    public void setService(ServiceJobs service) {
        this.service = service;
    }

    public CarMaintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(CarMaintenance maintenance) {
        this.maintenance = maintenance;
    }
}
