package pl.edu.pwr.zpi.autoasystent.presenters;

import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

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
    RefuelPanel panel;

    public RefuelPresenter(RefuelPanel panel) {
        this.panel = panel;
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO implement
        //  panel.startActivity(RefuelActivity.class, null);
        panel.startActivity(RefuelViewActivity.class, Uri.parse(((Refueling) parent.getItemAtPosition(position)).getId().toString()));
    }

    public void setList() {
        List<Refueling> refuelings = RefuelingService.getInstance().getAllRefuelings();
        panel.setRefuelList(refuelings);
    }


    public void onAddButtonClick(View v) {
        //TODO implement - chyba jest, nie?
        panel.startActivity(RefuelingAddActivity.class, null);
      }
}

