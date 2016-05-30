package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.view.ReportFuelPanel;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportFuelPresenter {
    ReportFuelPanel panel;
    private long carId;
    private Date fromDate;
    private Date toDate;

    public ReportFuelPresenter(ReportFuelPanel panel, long carId, Date fromDate, Date toDate) {
        this.panel = panel;
        this.carId = carId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void setList() {
        List<Refueling> refuelings = RefuelingService.getInstance().getRefuelingsByCarAndDate(carId, fromDate, toDate);
        panel.setReportData(refuelings);
    }
}
