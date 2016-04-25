package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.view.RefuelViewPanel;

/**
 * Created by Marek on 25.04.2016.
 */
public class RefuelViewPresenter {
    RefuelViewPanel panel;

    public RefuelViewPresenter(RefuelViewPanel panel) {
        this.panel = panel;
    }

    public void setRefueling(long id) {
        Refueling refueling = RefuelingService.getInstance().findRefuelingById(id);
        panel.setRefuelingData(refueling);
    }

}
