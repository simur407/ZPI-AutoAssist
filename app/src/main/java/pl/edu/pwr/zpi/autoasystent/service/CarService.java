package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Car;

/**
 * Klasa wspomagająca zapis, odczyt, wyszukiwanie itp. z bazy. Serwis dla klasy {@link pl.edu.pwr.zpi.autoasystent.model.Car}
 *
 * @author Szymon Bartczak
 * @date 2016-04-10
 */
public class CarService {

    private static CarService instance = null;

    public static CarService getInstance() {
        if(instance == null) {
            instance = new CarService();
        }
        return instance;
    }

    private CarService() {

    }

    public void saveCar(Car car) {
        Car.save(car);
    }

    public Car findCarById(long id) {
        return Car.findById(Car.class, id);
    }

    public List<Car> getAllCars() {
        //return Car.listAll(Car.class);
        /*List<Car> temp = new ArrayList<>();
        Car car = new Car();
        Make make = new Make();
        make.setMakeName("Opel");
        Model model = new Model();
        model.setMake(make);
        model.setModelName("Astra");
        car.setModel(model);
        temp.add(car);*/

        return Car.listAll(Car.class);
        // return temp;
    }

    public void deleteCar(Car car) {
        Car.delete(car);
    }
}
