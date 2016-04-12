package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

/**
 * Created by Szymon on 2016-04-02.
 */
public class CarMaintenance extends SugarRecord {//Zmieniłem nazwe bo kłuciła się z serwisem ;)

    private String maintenanceName;

    public CarMaintenance() {

    }

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }
}
