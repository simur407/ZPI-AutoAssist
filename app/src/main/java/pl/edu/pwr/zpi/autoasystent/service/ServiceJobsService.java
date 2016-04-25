package pl.edu.pwr.zpi.autoasystent.service;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by argo on 11 kwi.
 */

public class ServiceJobsService {

    private static ServiceJobsService instance = null;

    public static ServiceJobsService getInstance() {
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
         // return ServiceJobs.findById(ServiceJobs.class, id);
         //TODO usunąć to poniżej
         ServiceJobs serviceJobs = new ServiceJobs();
         serviceJobs.setServiceGarage("Mechanik");
         serviceJobs.setServiceCost(250.66);
         serviceJobs.setServiceDescription("fajnie było");
         serviceJobs.setId(18L);
         return serviceJobs;
    }

    public java.util.List<ServiceJobs> getAllServices() {
        return ServiceJobs.listAll(ServiceJobs.class);
    }

    public void deleteService(ServiceJobs service) {
        ServiceJobs.delete(service);
    }
}
