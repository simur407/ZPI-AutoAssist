package pl.edu.pwr.zpi.autoasystent.view;

import pl.edu.pwr.zpi.autoasystent.model.Car;

/**
 * Created by Szymon on 2016-03-19.
 * Intefejs który będzie podpięty do aktywności/fragmentu. Na jego podstawie prezenter będzie wykonywał działania na widoku.
 */
public interface CarDetailsPanel {
    void setCar(Car car);
}
