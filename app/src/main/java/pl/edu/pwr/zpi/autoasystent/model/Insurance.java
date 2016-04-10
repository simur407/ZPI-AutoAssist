package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Marcin on 11.04.2016.
 */
public class Insurance extends SugarRecord {

    private Car car;
    private Date insuranceDate;
    private String insuranceDescription;
    private double insuranceCost;

    public Insurance()
    {

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getInsuranceDescription() {
        return insuranceDescription;
    }

    public void setInsuranceDescription(String insuranceDescription) {
        this.insuranceDescription = insuranceDescription;
    }

    public double getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(double insuranceCost) {
        this.insuranceCost = insuranceCost;
    }
}
