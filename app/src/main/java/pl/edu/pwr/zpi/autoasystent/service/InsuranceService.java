package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;

/**
 * Created by Marcin on 11.04.2016.
 */
public class InsuranceService {
    private static InsuranceService instance = null;

    public static InsuranceService getInstance() {
        if (instance == null) {
            instance = new InsuranceService();
        }
        return instance;
    }

    private InsuranceService() {

    }

    public void saveInsurance(Insurance insurance) {
        Insurance.save(insurance);
    }

    public Insurance findInsuranceById(long id) {
        return Insurance.findById(Insurance.class, id);
    }

    public List<Insurance> getAllInsurances() {
        return Insurance.listAll(Insurance.class);
    }

    public void deleteInsurance(Insurance insurance) {
        Insurance.delete(insurance);
    }

    public Insurance getLatest(Car car) {
        List<Insurance> insuranceList = Insurance.find(Insurance.class, "car = ?", new String[]{String.valueOf(car.getId())}, null,
                "insurance_date DESC", "1");

        if (!insuranceList.isEmpty()) {
            return insuranceList.get(0);
        }
        return null;
    }
}
    

