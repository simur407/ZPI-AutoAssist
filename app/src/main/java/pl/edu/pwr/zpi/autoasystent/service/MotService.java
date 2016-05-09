package pl.edu.pwr.zpi.autoasystent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Mot;

/**
 * Created by Marcin on 11.04.2016.
 */
public class MotService {
    private static MotService instance = null;

    public static MotService getInstance() {
        if(instance == null) {
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

    public void deleteMot(Mot mot) {
        Mot.delete(mot);
    }
    
    public Date getLatest(Car car) {
        ArrayList<Mot> motList=(ArrayList<Mot>)getAllMots();
        Date motDate=new Date(1);
        for(Mot mot:motList)
        {
            Date motDateTemp=mot.getMotDate();
            if (car.equals(mot.getCar()))
            {
                if (motDateTemp.compareTo(motDate) > 0)
                {
                    motDate=motDateTemp;
                }
            }
        }
        return motDate;
    }
}

