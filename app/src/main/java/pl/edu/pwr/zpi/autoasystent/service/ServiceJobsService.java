package pl.edu.pwr.zpi.autoasystent.service;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by argo on 11 kwi.
 */

public class ServiceJobsService {

    private ServiceJobsService instance = null;

    public ServiceJobsService getInstance() {
        if (instance == null) {
            instance = new ServiceJobsService();
        }
        return instance;
    }

    private ServiceJobsService() {

    }

    public void saveService(ServiceJobs service) {
        ServiceJobs.save(service);
    }

     public ServiceJobs findServiceById(long id) {
        return ServiceJobs.findById(ServiceJobs.class, id);
    }

    public java.util.List<ServiceJobs> getAllServices() {
        return ServiceJobs.listAll(ServiceJobs.class);
    }

    public void deleteService(ServiceJobs service) {
        ServiceJobs.delete(service);
    }
}
