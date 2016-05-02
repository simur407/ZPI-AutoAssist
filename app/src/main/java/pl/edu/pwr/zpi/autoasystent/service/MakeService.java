package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Make;

/**
 * Created by Marek on 10.04.2016.
 */
public class MakeService {

    private static MakeService instance = null;

    public static MakeService getInstance() {
        if (instance == null) {
            instance = new MakeService();
        }
        return instance;
    }

    private MakeService() {

    }

    public void saveMake(Make make) {
        Make.save(make);
    }

    public void deleteMake(Make make) {
        Make.delete(make);
    }

    public boolean isEmpty() {
        return Make.count(Make.class) == 0;
    }

    public void saveMakeList(List<Make> makes) {
        Make.saveInTx(makes);
    }

    public List<Make> getAllMakes() {
        return Make.listAll(Make.class);
    }

    public Make findMakeByName(String makeName) {
        List<Make> makes = Make.find(Make.class, "make_name = ?", makeName);
        if (!makes.isEmpty()) {
            return makes.get(0);
        }
        return null;
    }
}
