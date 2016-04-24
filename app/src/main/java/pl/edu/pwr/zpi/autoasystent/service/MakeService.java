package pl.edu.pwr.zpi.autoasystent.service;

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
        return false;
    }
}
