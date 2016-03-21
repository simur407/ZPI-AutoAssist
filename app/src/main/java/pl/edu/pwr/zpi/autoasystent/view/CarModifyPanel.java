package pl.edu.pwr.zpi.autoasystent.view;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;

/**
 * Created by Szymon on 2016-03-21.
 */
public interface CarModifyPanel {
    void showColorPicker();
    void setMakeSpinner(List<Make> makeList);
    void setModelSpinner(List<Model> modelList);
    void showPhotoDialog();
    void setColor(int i);
}
