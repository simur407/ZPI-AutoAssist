package pl.edu.pwr.zpi.autoasystent.view;

import android.net.Uri;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by Marek on 25.04.2016.
 */
public interface ServiceJobsPanel {
    void setServiceJobsList(List<ServiceJobs> serviceJobsList);

    void startActivity(Class<?> clazz, Uri additionalData);
}
