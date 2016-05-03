package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

/**
 * Konkretna naprawa przy wizycie u mechanika.
 */
public class CarMaintenance extends SugarRecord {//Zmieniłem nazwe bo kłóciła się z serwisem ;)

    private String maintenanceName;

    public CarMaintenance() {

    }

    public CarMaintenance(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }
}
