package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.service.MakeService;
import pl.edu.pwr.zpi.autoasystent.service.ModelService;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-23
 */
public class MakeModelLoader {

    public static void load(Resources resources) {
        Scanner scanner = new Scanner(resources.openRawResource(R.raw.make_model));
        scanner.nextLine();//Pomijam nagłówek
        List<Make> makes = new ArrayList<>();
        List<Model> models = new ArrayList<>();
        Make make = null;
        while(scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String[] columns = row.split(",");
            for (int i = 0; i < columns.length; i++) {
                columns[i] = columns[i].replaceAll("\"", "");
            }
            if(make == null || !existsInList(makes, columns[1])) {
                make = new Make();
                make.setMakeName(columns[1]);
                makes.add(make);
            }

            Model model = new Model();
            model.setModelName(columns[2]);
            model.setMake(make);
            models.add(model);
        }
        MakeService.getInstance().saveMakeList(makes);
        ModelService.getInstance().saveModelList(models);
    }

    private static boolean existsInList(List<Make> list, String name) {
        for (Make m :
                list) {
            if (m.getMakeName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
