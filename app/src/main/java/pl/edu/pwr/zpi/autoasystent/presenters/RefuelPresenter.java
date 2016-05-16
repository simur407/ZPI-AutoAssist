package pl.edu.pwr.zpi.autoasystent.presenters;

import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import com.rafalzajfert.androidlogger.Logger;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.view.RefuelPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.RefuelViewActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.RefuelingAddActivity;

/**
 * Created by Marek on 24.04.2016.
 */
public class RefuelPresenter {
    private RefuelPanel panel;
    private long carId;

    public RefuelPresenter(RefuelPanel panel, long carId) {
        this.panel = panel;
        this.carId = carId;
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO implement
        //  panel.startActivity(RefuelActivity.class, null);
        panel.startActivity(RefuelViewActivity.class, Uri.parse(((Refueling) parent.getItemAtPosition(position)).getId().toString()));
    }

    public void setList() {
        List<Refueling> refuelings = RefuelingService.getInstance().getAllRefuelingsByCarId(carId);
        panel.setRefuelList(refuelings);
    }


    public void onAddButtonClick(View v) {
        panel.startActivity(RefuelingAddActivity.class, Uri.parse(String.valueOf(carId)));
      }

    public void onListItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        panel.showDeleteMenu((Refueling) parent.getItemAtPosition(position));
    }

    public void deleteRefueling(Refueling refueling) {
        if(carId != refueling.getCar().getId()) {
            Logger.error("Deleting refueling from other car!!!");
        }
        RefuelingService.getInstance().deleteRefueling(refueling);
        List<Refueling> refuelings = RefuelingService.getInstance().getAllRefuelingsByCarId(carId);
        panel.setRefuelList(refuelings);
    }
}

