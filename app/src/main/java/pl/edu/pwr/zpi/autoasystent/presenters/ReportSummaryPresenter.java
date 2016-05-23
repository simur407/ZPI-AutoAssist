package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.InsuranceService;
import pl.edu.pwr.zpi.autoasystent.service.MotService;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.ReportSummaryPanel;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportSummaryPresenter {
    ReportSummaryPanel panel;
    private long carId;
    private Date fromDate;
    private Date toDate;

    public ReportSummaryPresenter(ReportSummaryPanel panel, long carId, Date fromDate, Date toDate) {
        this.panel = panel;
        this.carId = carId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    //  public void setReportData(long carId){
    //      panel.setReportData(Car.findById(Car.class, carId));
    //  }

    public void setList() {
        List<Refueling> refuelings = RefuelingService.getInstance().getRefuelingsByCarAndDate(carId, fromDate, toDate);
        List<ServiceJobs> serviceJobses = ServiceJobsService.getInstance().getAllServicesByCarIdAndDates(carId, fromDate, toDate);
        List<Mot> mots = MotService.getInstance().getAllMotsByCarId(carId);
        List<Insurance> insurances = InsuranceService.getInstance().getAllInsurancesByCarId(carId);
        Car car = CarService.getInstance().findCarById(carId);
        panel.setReportData(refuelings, serviceJobses, mots, insurances, car);

    }
}
