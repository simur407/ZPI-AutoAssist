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

    public Refueling findRefuelingById(long id) {
        return Refueling.findById(Refueling.class, id);
    }

    public void deleteRefueling(Refueling refueling) {
        Refueling.delete(refueling);
    }

    public List<Refueling> getAllRefuelings() {
        return Refueling.listAll(Refueling.class);
    }

    public List<Refueling> getAllRefuelingsByCarId(long carId) {
        return Refueling.find(Refueling.class, "car = ?", String.valueOf(carId));
    }

    public List<Refueling> getRefuelingsByCarAndDate(long carId, Date from, Date to) {
        return Refueling.find(Refueling.class, "car = ? AND refueling_date BETWEEN ? AND ?", new String[]{String.valueOf(carId),
                String.valueOf(from.getTime()), String.valueOf(to.getTime())}, null, "refueling_mileage ASC", null);
    }

    public int getRefuelingMaxMileage(long carId) {
        List<Refueling> refuelings =
                Refueling.find(Refueling.class, "car = ?", new String[]{String.valueOf(carId)}, null, "refueling_mileage DESC", "1");
        if (!refuelings.isEmpty()) {
            return refuelings.get(0).getRefuelingMileage();
        }
        return 0;
    }

    public void saveRefueling(Refueling refueling) {
        Refueling.save(refueling);
    }
}
