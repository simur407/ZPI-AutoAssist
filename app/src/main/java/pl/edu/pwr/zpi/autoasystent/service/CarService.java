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

    //Tak, wiem, że pisanie takich metod może wyglądać bezsensownie, ale ma sens i takie wlasnie piszemy.
    //Pisząc to myślcie jakie konkretnie zapytania do bazy będziecie kierować. I wpiszcie sobie <Nazwa modelu> i wcisnijcie CTRL+Spacja
    //Popatrzcie jakie macie metody tam. Zeby nie pałować się z query za każym razem :D

    private static CarService instance = null;

    //Nie musi być singletonem, ale uznałem, że tak będzie bardziej pro i wygodniej ;)
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
        return Car.listAll(Car.class);
    }

    public void deleteCar(Car car) {
        Car.delete(car);
    }
}
