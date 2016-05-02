package pl.edu.pwr.zpi.autoasystent.presenters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.flask.colorpicker.OnColorSelectedListener;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.service.MakeService;
import pl.edu.pwr.zpi.autoasystent.service.ModelService;
import pl.edu.pwr.zpi.autoasystent.view.CarModifyPanel;

/**
 * Created by Szymon on 2016-03-21.
 */
public abstract class CarModifyPresenter implements OnColorSelectedListener {

    protected CarModifyPanel panel;

    public CarModifyPresenter(CarModifyPanel panel) {
        this.panel = panel;
    }

    public void saveCar(@NonNull Car car, Make make, Model model) {
        if(make.getId() == null) {
            MakeService.getInstance().saveMake(make);
        }
        if(model.getId() == null) {
            model.setMake(make);
            ModelService.getInstance().saveModel(model);
        }
        car.setModel(model);
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

    public void setMakeSpinner() {
        List<Make> makes = MakeService.getInstance().getAllMakes();
        panel.setMakeSpinner(makes);
    }

    public void onMakeSelected(AdapterView<?> parent, View view, int position, long id) {
        Make make = ((Make)parent.getAdapter().getItem(position));
        List<Model> models = ModelService.getInstance().getModelsByMake(make);
        panel.setModelSpinner(models);
    }
}
