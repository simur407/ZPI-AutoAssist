package pl.edu.pwr.zpi.autoasystent.presenters;

import android.support.v4.app.Fragment;

import java.util.LinkedList;

import pl.edu.pwr.zpi.autoasystent.view.CarPanel;
import pl.edu.pwr.zpi.autoasystent.view.fragment.RefuelListFragment;

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

    public void setFragments() {
        LinkedList<Fragment> fragmentLinkedList = new LinkedList<>();
        fragmentLinkedList.add(new RefuelListFragment());
        //  fragmentLinkedList.add(new RefuelViewFragment());
        panel.setFragments(fragmentLinkedList);
    }
}
