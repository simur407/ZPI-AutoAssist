package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;
import java.util.Date;

/**
 * Created by Szymon on 2016-04-02.
 */
public class ServiceJobs extends SugarRecord {

    private Car car;

    private Date serviceDate;

    private double serviceCost;

    private String garage;

    private int serviceMileage;

    private String serviceDescription;

    public ServiceJobs() {
    }

    public ServiceJobs(Car car, Date serviceDate, double serviceCost, String garage, int serviceMileage, String serviceDescription) {
        this.car = car;
        this.serviceDate = serviceDate;
        this.serviceCost = serviceCost;
        this.garage = garage;
        this.serviceMileage = serviceMileage;
        this.serviceDescription = serviceDescription;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getGarage() {
        return garage;
    }

    public int getServiceMileage() {
        return serviceMileage;
    }

    public void setServiceMileage(int serviceMileage) {
        this.serviceMileage = serviceMileage;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

}
