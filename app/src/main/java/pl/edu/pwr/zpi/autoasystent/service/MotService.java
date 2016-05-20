package pl.edu.pwr.zpi.autoasystent.service;

import android.support.annotation.Nullable;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Mot;

/**
 * Created by Marcin on 11.04.2016.
 */
public class MotService {
    private static MotService instance = null;

    public static MotService getInstance() {
        if (instance == null) {
            instance = new MotService();
        }
        return instance;
    }

    private MotService() {

    }

    public void saveMot(Mot mot) {
        Mot.save(mot);
    }

    public Mot findMotById(long id) {
        return Mot.findById(Mot.class, id);
    }

    public List<Mot> getAllMots() {
        return Mot.listAll(Mot.class);
    }

    public List<Mot> getAllMotsByCarId(long carId) {
        return Mot.find(Mot.class, "car = ?", String.valueOf(carId));
    }

    public void deleteMot(Mot mot) {
        Mot.delete(mot);
    }

    @Nullable
    public Mot getLatest(Car car) {
        List<Mot> mots = Mot.find(Mot.class, "car = ?", new String[]{String.valueOf(car.getId())}, null, "mot_date DESC", "1");
        if (!mots.isEmpty()) {
            return mots.get(0);
        }
        return null;
    }
}

