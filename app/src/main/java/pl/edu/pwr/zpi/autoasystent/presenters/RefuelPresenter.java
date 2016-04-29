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
        //Toast.makeText((Context)panel, "Clicked " + position, Toast.LENGTH_SHORT).show();
        //TODO implement
        //  panel.startActivity(RefuelActivity.class, null);
        panel.startActivity(RefuelViewActivity.class, Uri.parse(((Refueling) parent.getItemAtPosition(position)).getId().toString()));
    }

    public void setList() {
        List<Refueling> refuelings = RefuelingService.getInstance().getAllRefuelings();
        refuelings.add(RefuelingService.getInstance().findRefuelingById(1)); //TODO Wywalic
        panel.setRefuelList(refuelings);
    }


    public void onAddButtonClick(View v) {
        //TODO implement
        panel.startActivity(RefuelingAddActivity.class, null);
        // panel.startActivity(RefuelViewActivity.class, null);
        // panel.startActivity(ServiceJobsActivity.class, Uri.parse());
    }
}

