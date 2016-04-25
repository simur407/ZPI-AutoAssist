package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Model tankowania samochodu.
 *
 * @author Szymon Bartczak
 * @date 2016-04-07
 */
public class Refueling extends SugarRecord {
    //ładnie opisałem cały proceder w Car, patrz tam

    private Car car;

    private Date refuelingDate;

    private int refuelingMileage;

    private int quantity;

    private Double refuelingCost;

    private String refuelingDescription;


    public Refueling() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getRefuelingDate() {
        return refuelingDate;
    }

    public void setRefuelingDate(Date refuelingDate) {
        this.refuelingDate = refuelingDate;
    }

    public int getRefuelingMileage() {
        return refuelingMileage;
    }

    public void setRefuelingMileage(int refuelingMileage) {
        this.refuelingMileage = refuelingMileage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRefuelingCost() {
        return refuelingCost;
    }

    public void setRefuelingCost(Double refuelingCost) {
        this.refuelingCost = refuelingCost;
    }

    public String getRefuelingDescription() {
        return refuelingDescription;
    }

    public void setRefuelingDescription(String refuelingDescription) {
        this.refuelingDescription = refuelingDescription;
    }
}
