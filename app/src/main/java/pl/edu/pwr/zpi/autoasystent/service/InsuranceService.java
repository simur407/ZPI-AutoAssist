package pl.edu.pwr.zpi.autoasystent.service;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;

/**
 * Created by Marcin on 11.04.2016.
 */
public class InsuranceService {
    private static InsuranceService instance = null;
    
    public static InsuranceService getInstance() {
        if(instance == null) {
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

    public Date getLatest(Car car) {
        ArrayList<Insurance> insuranceList=(ArrayList<Insurance>)getAllInsurances();
        Date insuranceDate=new Date(1);
        for(Insurance insurance:insuranceList)
        {
            Date insuranceDateTemp=insurance.getInsuranceDate();
            if (car.equals(insurance.getCar()))
            {
                if (insuranceDateTemp.compareTo(insuranceDate)==1)
                {
                    insuranceDate=insuranceDateTemp;
                }
            }
        }
        return insuranceDate;
    }
}
    

