package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;

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

    public void saveCar(Car car) {
        Car.save(car);
    }

    public Car findCarById(long id) {
        return Car.findById(Car.class, id);
    }

    public List<Car> getAllCars() {
        return Car.listAll(Car.class);
    }

    public void deleteCar(Car car) {
        Car.delete(car);
    }
}

