package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

/**
 * Konkretna naprawa przy wizycie u mechanika.
 */
public class CarMaintenance extends SugarRecord {//Zmieniłem nazwe bo kłóciła się z serwisem ;)

    private String maintenanceNameEng;
    private String maintenanceNameDeu;
    private String maintenanceNamePol;

    public CarMaintenance() {

    }

    public CarMaintenance(String maintenanceName) {
        this.maintenanceNameEng = maintenanceName;
    }

    public CarMaintenance(String maintenanceNameEng, String maintenanceNameDeu, String maintenanceNamePol) {
        this.maintenanceNameEng = maintenanceNameEng;
        this.maintenanceNameDeu = maintenanceNameDeu;
        this.maintenanceNamePol = maintenanceNamePol;
    }


    public String getMaintenanceNameEng() {
        return maintenanceNameEng;
    }

    public String getMaintenanceNameDeu() {
        return maintenanceNameDeu;
    }

    public String getMaintenanceNamePol() {
        return maintenanceNamePol;
    }


    public void setMaintenanceNameEng(String maintenanceNameEng) {
        this.maintenanceNameEng = maintenanceNameEng;
    }

    public void setMaintenanceNameDeu(String maintenanceNameDeu) {
        this.maintenanceNameDeu = maintenanceNameDeu;
    }

    public void setMaintenanceNamePol(String maintenanceNamePol) {
        this.maintenanceNamePol = maintenanceNamePol;
    }
}
