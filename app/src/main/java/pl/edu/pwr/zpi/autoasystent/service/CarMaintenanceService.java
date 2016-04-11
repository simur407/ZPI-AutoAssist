package pl.edu.pwr.zpi.autoasystent.service;

import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;

/**
 * Created by argo on 11 kwi.
 */

public class CarMaintenanceService {

    private CarMaintenanceService instance = null;

    public CarMaintenanceService getInstance() {
        if (instance == null) {
            instance = new CarMaintenanceService();
        }
        return instance;
    }

    private CarMaintenanceService() {

    }

    public void saveMaintenance (CarMaintenance maintenance) {
        CarMaintenance.save(maintenance);
    }

    public void deleteMaintenance (CarMaintenance maintenance) {
        CarMaintenance.delete(maintenance);
    }
}
