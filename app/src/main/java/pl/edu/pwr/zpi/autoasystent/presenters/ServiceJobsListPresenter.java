package pl.edu.pwr.zpi.autoasystent.presenters;

import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import com.rafalzajfert.androidlogger.Logger;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.service.ServiceJobsService;
import pl.edu.pwr.zpi.autoasystent.view.ServiceJobsPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.ServiceJobsActivity;

/**
 * Created by Marek on 25.04.2016.
 */
public class ServiceJobsListPresenter {
    ServiceJobsPanel panel;

    public ServiceJobsListPresenter(ServiceJobsPanel panel) {
        this.panel = panel;
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        Logger.debug(((ServiceJobs) parent.getItemAtPosition(position)).getId());
        panel.startActivity(ServiceJobsActivity.class, Uri.parse(((ServiceJobs) parent.getItemAtPosition(position)).getId().toString()));
    }

    public void setList() {
        List<ServiceJobs> serviceJobs = ServiceJobsService.getInstance().getAllServices();
        serviceJobs.add(ServiceJobsService.getInstance().findServiceById(1)); //TODO Wywalic
        panel.setServiceJobsList(serviceJobs);
    }

    public void onAddButtonClick(View v) {
        //TODO implement
        // panel.startActivity(ServiceActivity.class, null);
    }
}
