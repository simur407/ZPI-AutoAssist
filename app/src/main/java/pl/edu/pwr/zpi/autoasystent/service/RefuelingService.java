package pl.edu.pwr.zpi.autoasystent.service;

import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Refueling;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-11
 */
public class RefuelingService {

    private static RefuelingService instance = null;

    public static RefuelingService getInstance() {
        if (instance == null) {
            instance = new RefuelingService();
        }

        return instance;
    }

    private RefuelingService() {
    }

    public void addRefueling(Refueling refueling) {
        Refueling.save(refueling);
    }

    public Refueling findRefuelingById(long id) {
        // return Refueling.findById(Refueling.class, id);
        Refueling refueling = new Refueling();
        refueling.setQuantity(50.5);
        refueling.setRefuelingCost(50.55);
        refueling.setId(18L);
        return refueling;
    }

    public void deleteRefueling(Refueling refueling) {
        Refueling.delete(refueling);
    }

    public List<Refueling> getAllRefuelings() {
        return Refueling.listAll(Refueling.class);
    }

    public List<Refueling> getRefuelingsByDate(Date from, Date to) {
        return Refueling.find(Refueling.class, "refueling_date BETWEEN ? AND ?",
                String.valueOf(from.getTime()), String.valueOf(to.getTime()));
    }
}
