package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.service.RefuelingService;
import pl.edu.pwr.zpi.autoasystent.view.activity.RefuelingAddActivity;

/**
 * Created by Marcin on 25.04.2016.
 */
public class RefuelingAddPresenter {

    RefuelingAddActivity activity;

    public RefuelingAddPresenter(RefuelingAddActivity activity) {
        this.activity=activity;
    }

    public void saveRefueling(Refueling refueling) {
        RefuelingService.getInstance().saveRefueling(refueling);
    }
}
