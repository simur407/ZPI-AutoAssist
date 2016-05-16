package pl.edu.pwr.zpi.autoasystent.view;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-23
 */
public interface CarPanel {

    void setFragments(List<Fragment> fragments);

    void setTitle(String title);
}
