package pl.edu.pwr.zpi.autoasystent.view;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;

/**
 * Created by Szymon on 2016-04-02.
 */
public interface CarAddServicePanel {
    void setMaintanaceList(List<CarMaintenance> maintenances);

    /**
     * Pokazuje dialog do wyboru daty przeglądu.
     * @param date wybrana aktualnie data.
     */
    void showDataPicker(Date date);

    /**
     * Ustawia aktualną datę na samym początku.
     */
    void setCurrentData();


}
