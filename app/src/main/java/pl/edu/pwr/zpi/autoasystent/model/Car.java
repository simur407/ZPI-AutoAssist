package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;

/**
 * Model samochodu.
 *
 * Created by Szymon on 2016-03-19.
 */
public class Car extends SugarRecord {

    //UWAGA! Nie podajemy tutaj id! Id jest samo generowane!

    private Model model; //zamiast ID piszemy caly obiekt, będzie dołączana automatycznie referencja

    private String VIN;

    private Date productionYear;

    private String licencePlate;

    private int capacity;

    private String color;

    private int power;

    private int startMileage;

    private String carDescription;
    /**
     * Pusty konstruktor potrzebny dla SugarORMa!
     */
    public Car() {

    }

    //Po napisaniu wszystkich pól wciskasz Alt+Insert, Getters & Setters, zaznaczasz wszystkie pola i klikasz ok.

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Date getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Date productionYear) {
        this.productionYear = productionYear;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(int startMileage) {
        this.startMileage = startMileage;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

}
