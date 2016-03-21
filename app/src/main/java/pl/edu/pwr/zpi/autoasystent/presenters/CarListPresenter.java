package pl.edu.pwr.zpi.autoasystent.presenters;

import android.view.View;
import android.widget.AdapterView;

import pl.edu.pwr.zpi.autoasystent.view.CarListPanel;

/**
 * Created by Szymon on 2016-03-21.
 */
public class CarListPresenter {

    CarListPanel panel;

    public CarListPresenter(CarListPanel panel) {
        this.panel = panel;
    }

    public void setList() {
        panel.setCarList(null);//TODO Database select
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO implement
    }

    public void onAddButtonClick(View v) {
        //TODO implement

        panel.refreshList();
    }
}
