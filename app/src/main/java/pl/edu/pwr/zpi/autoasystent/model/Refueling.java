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

    private double refuelnigCost;

    private String refuelingDescription;


    public Refueling() {
    }

}
