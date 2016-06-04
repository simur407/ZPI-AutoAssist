package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.RefersTo;

/**
 * Created by Marek on 03.06.2016.
 */
public class RefersToService {

    private static RefersToService instance = null;

    public static RefersToService getInstance() {
        if (instance == null) {
            instance = new RefersToService();
        }
        return instance;
    }


    public List<RefersTo> getRefersToByServiceId(long serviceId) {
        return RefersTo.find(RefersTo.class, "service = ?", String.valueOf(serviceId));
    }
}
