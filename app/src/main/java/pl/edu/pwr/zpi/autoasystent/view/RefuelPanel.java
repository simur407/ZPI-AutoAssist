package pl.edu.pwr.zpi.autoasystent.view;

import android.net.Uri;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;

/**
 * Created by Marek on 24.04.2016.
 */
public interface RefuelPanel {
    void setRefuelList(List<Refueling> refuelingList);

    void startActivity(Class<?> clazz, Uri additionalData);

}
