package pl.edu.pwr.zpi.autoasystent.presenters;

import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarListPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.AchievementActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarModifyActivity;
import pl.edu.pwr.zpi.autoasystent.view.activity.SettingsActivity;

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
        Car car = (Car) parent.getItemAtPosition(position);
        panel.startActivity(CarActivity.class, Uri.parse(String.valueOf(car.getId())));
    }

    public void onAddButtonClick(View v) {
        panel.startActivity(CarModifyActivity.class, null);
    }

    public void onListItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        panel.showDeleteMenu((Car)parent.getItemAtPosition(position));
    }

    public void editCar(Car car) {
        panel.startActivity(CarModifyActivity.class, Uri.parse(String.valueOf(car.getId())));
    }

    public void deleteCar(Car car) {
        CarService.getInstance().deleteCar(car);
        List<Car> cars = CarService.getInstance().getAllCars();
        panel.setCarList(cars);
        panel.refreshList();
    }

    public void onSettingsSelected() {
        panel.startActivity(SettingsActivity.class, null);
    }

    public void onAchievementsSelected() {
        panel.startActivity(AchievementActivity.class, null);
    }

    public void showConfirmDialog(Car car) {
        panel.showConfirmDialog(car);
    }
}
