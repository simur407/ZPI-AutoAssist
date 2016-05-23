package pl.edu.pwr.zpi.autoasystent.presenters;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.ReportServicesPanel;

/**
 * Created by Marek on 04.05.2016.
 */
public class ReportServicesPresenter {
    ReportServicesPanel panel;
    private long carId;
    private Date fromDate;
    private Date toDate;

    public ReportServicesPresenter(ReportServicesPanel panel, long carId, Date fromDate, Date toDate) {
        this.panel = panel;
        this.carId = carId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void setList() {
        List<ServiceJobs> serviceJobses = ServiceJobsService.getInstance().getAllServicesByCarIdAndDates(carId, fromDate, toDate);
        panel.setReportData(serviceJobses);
    }
}
