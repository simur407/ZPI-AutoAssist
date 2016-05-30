package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.service.CarMaintenanceService;
import pl.edu.pwr.zpi.autoasystent.service.MakeService;
import pl.edu.pwr.zpi.autoasystent.service.ModelService;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DialogDismissListener;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-23
 */
public class InitLoader {

    public static void startAsyncLoad(final Resources resources, final DialogDismissListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadMaintenances(resources);
                load(resources);
                listener.dismissDialog();
            }
        }).start();
    }

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
            } else {
                make = findMakeInMakeList(makes, columns[1]);
                if(make == null) {
                    throw new IllegalStateException("Make cannot be null! Should be in maintenancesList!");
                }
            }

            if(!existsInModelList(models, columns[2])) {
                Model model = new Model();
                model.setModelName(columns[2]);
                model.setMake(make);
                models.add(model);
            }
        }
        MakeService.getInstance().saveMakeList(makes);
        ModelService.getInstance().saveModelList(models);
    }

    public static void loadMaintenances(Resources resources) {

        Scanner scanner = new Scanner(resources.openRawResource(R.raw.maintenances));

        List<CarMaintenance> maintenances = new ArrayList<>();

        CarMaintenance maintenance;
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String[] columns = row.split(",");
            maintenance = new CarMaintenance();
            maintenance.setMaintenanceNameEng(columns[0]);
            maintenance.setMaintenanceNameDeu(columns[1]);
            maintenance.setMaintenanceNamePol(columns[2]);
            maintenances.add(maintenance);
        }
        CarMaintenanceService.getInstance().saveMaintenanceList(maintenances);
    }

    private static boolean existsInModelList(List<Model> models, String name) {
        for (Model m : models) {
            if (m.getModelName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static Make findMakeInMakeList(List<Make> list, String name) {
        for (Make m : list) {
            if (m.getMakeName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    private static boolean existsInList(List<Make> list, String name) {
        for (Make m : list) {
            if (m.getMakeName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
