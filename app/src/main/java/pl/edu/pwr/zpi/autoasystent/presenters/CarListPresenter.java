package pl.edu.pwr.zpi.autoasystent.presenters;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarListPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarAddActivity;

/**
 * Created by Szymon on 2016-03-21.
 */
public class CarListPresenter {

    CarListPanel panel;

    public CarListPresenter(CarListPanel panel) {
        this.panel = panel;
    }

    public void setList() {
        List<Car> cars = CarService.getInstance().getAllCars();
        panel.setCarList(cars);
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText((Context)panel, "Clicked " + position, Toast.LENGTH_SHORT).show();
        //TODO implement
    }

    public void onAddButtonClick(View v) {
        panel.startActivity(CarAddActivity.class, null);
    }
}
