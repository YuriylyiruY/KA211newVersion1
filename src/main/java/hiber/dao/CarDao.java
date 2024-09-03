package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;

import java.util.List;

public interface CarDao {
    List<Car> listCars();

    void deleteCars();

    void add(Car car);
}
