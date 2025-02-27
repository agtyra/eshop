package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository implements ICarRepository {
    private final List<Car> carData = new ArrayList<>();

    @Override
    public Iterable<Car> findAll() { return carData; }

    @Override
    public Car findById(String id) {
        return carData.stream().filter(car -> car.getCarId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Car create(Car car) {
        if (car.getCarId() == null) {
            car.setCarId(UUID.randomUUID().toString());
        }
        carData.add(car);
        return car;
    }

    @Override
    public Car update(String id, Car updatedCar) {
        for (int i = 0; i < carData.size(); i++) {
            if (carData.get(i).getCarId().equals(id)) {
                carData.set(i, updatedCar);
                return updatedCar;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}

