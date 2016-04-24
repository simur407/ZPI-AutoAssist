package pl.edu.pwr.zpi.autoasystent.presenters;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.view.RefuelPanel;

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
    }

    public void setList() {
        List<Refueling> refuelings = RefuelingService.getInstance().getAllRefuelings();
        panel.setRefuelList(refuelings);
    }


    public void onAddButtonClick(View v) {
        //    panel.startActivity(RefuelAddActivity.class, null);
    }
}

