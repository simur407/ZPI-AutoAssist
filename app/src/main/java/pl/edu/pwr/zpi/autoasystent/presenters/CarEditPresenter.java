package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.view.CarEditPanel;

/**
 * Created by Szymon on 2016-03-21.
 */
public class CarEditPresenter extends CarModifyPresenter{

    public CarEditPresenter(CarEditPanel panel) {
        super(panel);
    }

    public void setInitialData() {
        ((CarEditPanel)panel).setInitialData(null);//TODO Database select
    }
}
