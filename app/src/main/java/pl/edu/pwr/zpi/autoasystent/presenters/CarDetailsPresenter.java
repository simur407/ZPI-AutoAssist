package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.view.CarDetailsPanel;

/**
 * Created by Szymon on 2016-03-19.
 * Odpowiedzialny jest za całą logikę, tutaj przekierowywuje się wszystko to co ma zrobić aktywność i stąd to wykonuje.
 */
public class CarDetailsPresenter {

    private CarDetailsPanel panel;

    public CarDetailsPresenter(CarDetailsPanel panel) {
        this.panel = panel;
    }

    public void setCar() {
        //TODO get car from database
        throw new UnsupportedOperationException();
        //panel.setCar();
    }

    //TODO more!
}