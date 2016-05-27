package pl.edu.pwr.zpi.autoasystent.service;

import java.util.Date;
import java.util.List;

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
        return ServiceJobs.findById(ServiceJobs.class, id);
    }

    public List<ServiceJobs> getAllServices() {
        return ServiceJobs.listAll(ServiceJobs.class);
    }

    public List<ServiceJobs> getAllServicesByCarId(long carId) {
        return ServiceJobs.find(ServiceJobs.class, "car = ?", String.valueOf(carId));
    }

    public List<ServiceJobs> getAllServicesByCarIdAndDates(long carId, Date from, Date to) {
        return ServiceJobs.find(ServiceJobs.class, "car = ? AND service_date BETWEEN ? AND ?", String.valueOf(carId), String.valueOf(from.getTime()), String.valueOf(to.getTime()));
    }

    public void deleteService(ServiceJobs service) {
        ServiceJobs.delete(service);
    }

    public int getServiceMaxMileage(long carId) {
        List<ServiceJobs> serviceJobses =
                ServiceJobs.find(ServiceJobs.class, "car = ?", new String[]{String.valueOf(carId)}, null, "service_mileage DESC", "1");
        if (!serviceJobses.isEmpty()) {
            return serviceJobses.get(0).getServiceMileage();
        }
        return 0;
    }
}
