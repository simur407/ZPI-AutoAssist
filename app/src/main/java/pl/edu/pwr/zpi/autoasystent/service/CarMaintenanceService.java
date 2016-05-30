package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;

/**
 * Created by argo on 11 kwi.
 */

public class CarMaintenanceService {

    private static CarMaintenanceService instance = null;

    public static CarMaintenanceService getInstance() {
        if (instance == null) {
            instance = new CarMaintenanceService();
        }
        return instance;
    }

    private CarMaintenanceService() {

    }

    public void saveMaintenanceList(List<CarMaintenance> maintenances) {
        CarMaintenance.saveInTx(maintenances);
    }

    public List<CarMaintenance> getMaintenancesList() {
        return CarMaintenance.listAll(CarMaintenance.class);
    }

    public void saveMaintenance (CarMaintenance maintenance) {
        CarMaintenance.save(maintenance);
    }

    public void deleteMaintenance (CarMaintenance maintenance) {
        CarMaintenance.delete(maintenance);
    }
}
