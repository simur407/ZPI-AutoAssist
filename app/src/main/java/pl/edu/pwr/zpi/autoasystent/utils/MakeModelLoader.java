package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.res.Resources;

import com.rafalzajfert.androidlogger.Logger;

import java.util.Scanner;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-23
 */
public class MakeModelLoader {

    public static void load(Resources resources) {
        Scanner scanner = new Scanner(resources.openRawResource(R.raw.make_model));
        scanner.next();//Pomijam nagłówek

        Make make = new Make();
        while(scanner.hasNext()) {
            String row = scanner.next();
            String[] columns = row.split(",");
            if(!columns[1].equals(make.getMakeName())) {
                make.setMakeName(columns[1]);
                Make.save(make);
            }

            Model model = new Model();
            model.setModelName(columns[2]);
            model.setMake(make);
            Model.save(model);
        }
        Logger.debug("Makes and models loaded successfully.");
    }
}
