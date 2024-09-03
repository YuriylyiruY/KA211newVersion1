package hiber.service;

import hiber.model.Car;

import java.util.List;

public interface CrService {

    void deleteCars();

    void add(Car car);

    List<Car> listCars();
}

