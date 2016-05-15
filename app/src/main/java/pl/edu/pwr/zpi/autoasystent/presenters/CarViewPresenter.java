package pl.edu.pwr.zpi.autoasystent.presenters;

import android.net.Uri;
import android.view.View;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.view.CarViewPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.InsuranceAddActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.MotAddActivity;

/**
 * Created by Marcin on 25.04.2016.
 */
public class CarViewPresenter {

    protected CarViewPanel panel;
    private long carId;

    public CarViewPresenter(CarViewPanel panel, long carId) {
        this.panel=panel;
        this.carId = carId;
    }

    public void setCarData(long carId) {
        panel.setCarData(Car.findById(Car.class, carId));
    }

    public void onInsuranceButtonClick(View v) {
        panel.startActivity(InsuranceAddActivity.class, Uri.parse(String.valueOf(carId)));
    }

    public void onMotButtonClick(View v) {
        panel.startActivity(MotAddActivity.class, Uri.parse(String.valueOf(carId)));
    }
}
