package pl.edu.pwr.zpi.autoasystent;

import com.orm.SugarApp;
import com.rafalzajfert.androidlogger.Logger;

import pl.edu.pwr.zpi.autoasystent.service.MakeService;
import pl.edu.pwr.zpi.autoasystent.utils.MakeModelLoader;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-24
 */
public class AutoAsystentApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        if(MakeService.getInstance().isEmpty()) {
            Logger.trace();
            MakeModelLoader.load(getResources());
        }
    }
}
