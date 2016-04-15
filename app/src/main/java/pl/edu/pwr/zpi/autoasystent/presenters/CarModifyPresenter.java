package pl.edu.pwr.zpi.autoasystent.presenters;

import com.flask.colorpicker.OnColorSelectedListener;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarModifyPanel;

/**
 * Created by Szymon on 2016-03-21.
 */
public abstract class CarModifyPresenter implements OnColorSelectedListener {

    protected CarModifyPanel panel;

    public CarModifyPresenter(CarModifyPanel panel) {
        this.panel = panel;
    }

    public void setSpinners() {
        panel.setMakeSpinner(null);
        panel.setModelSpinner(null);
        //TODO Database connection
    }

    public void saveCar(Car car) {
        CarService.getInstance().saveCar(car);
    }

    public void onCarPhotoClick() {
        panel.showPhotoDialog();
    }

    public void onColorPickerClick() {
        panel.showColorPicker();
    }

    @Override
    public void onColorSelected(int i) {
        panel.setColor(i);
    }
}
