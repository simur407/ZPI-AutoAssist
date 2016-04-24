package pl.edu.pwr.zpi.autoasystent;

import android.app.Application;

import pl.edu.pwr.zpi.autoasystent.service.MakeService;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-24
 */
public class AutoAsystentApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(MakeService.getInstance().isEmpty()) {

        }
    }
}
