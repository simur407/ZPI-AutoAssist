package pl.edu.pwr.zpi.autoasystent.presenters;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.LinkedList;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.service.CarService;
import pl.edu.pwr.zpi.autoasystent.view.CarPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;
import pl.edu.pwr.zpi.autoasystent.view.fragment.CarViewFragment;
import pl.edu.pwr.zpi.autoasystent.view.fragment.RefuelListFragment;
import pl.edu.pwr.zpi.autoasystent.view.fragment.ReportsFragment;
import pl.edu.pwr.zpi.autoasystent.view.fragment.ServiceJobsListFragment;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-23
 */
public class CarPresenter {

    CarPanel panel;


    public CarPresenter(CarPanel panel) {
        this.panel = panel;
    }

    public void setFragments(long carId) {
        LinkedList<Fragment> fragmentLinkedList = new LinkedList<>();
        fragmentLinkedList.add(new CarViewFragment());
        fragmentLinkedList.add(new RefuelListFragment());
        fragmentLinkedList.add(new ServiceJobsListFragment());
        fragmentLinkedList.add(new ReportsFragment());
        Bundle args = new Bundle();
        args.putLong(CarActivity.ID_KEY, carId);
        for (Fragment f : fragmentLinkedList) {
            f.setArguments(args);
        }
        panel.setFragments(fragmentLinkedList);
    }

    public void setToolbarTitle(long carId) {
        Car car = CarService.getInstance().findCarById(carId);
        panel.setTitle(car.getModel().getMake() + " " + car.getModel() );
    }
}
