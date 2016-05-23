package pl.edu.pwr.zpi.autoasystent.view;


import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by Marek on 04.05.2016.
 */
public interface ReportSummaryPanel {
    void setReportData(List<Refueling> refuelings, List<ServiceJobs> serviceJobses, List<Mot> mots, List<Insurance> insurances, Car car);

}
