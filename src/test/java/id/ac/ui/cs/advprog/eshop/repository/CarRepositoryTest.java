package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateCarWithId() {
        Car car = new Car();
        car.setCarId("123");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar);
        assertEquals("123", createdCar.getCarId());
        assertEquals("Toyota", createdCar.getCarName());
        assertEquals("Red", createdCar.getCarColor());
        assertEquals(5, createdCar.getCarQuantity());
        assertEquals(1, ((List<Car>) carRepository.findAll()).size());
    }

    @Test
    void testCreateCarWithoutId() {
        Car car = new Car();
        car.setCarName("Honda");

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar.getCarId());
        assertEquals("Honda", createdCar.getCarName());
        assertEquals(1, ((List<Car>) carRepository.findAll()).size());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        car1.setCarId("123");
        car1.setCarName("Mazda");

        Car car2 = new Car();
        car2.setCarId("456");
        car2.setCarName("BMW");

        carRepository.create(car1);
        carRepository.create(car2);

        Iterable<Car> cars = carRepository.findAll();
        assertEquals(2, ((List<Car>) cars).size());
    }

    @Test
    void testFindByIdSuccess() {
        Car car = new Car();
        car.setCarId("789");
        car.setCarName("Mercedes");

        carRepository.create(car);
        Car foundCar = carRepository.findById("789");

        assertNotNull(foundCar);
        assertEquals("789", foundCar.getCarId());
        assertEquals("Mercedes", foundCar.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        Car foundCar = carRepository.findById("999");
        assertNull(foundCar);
    }

    @Test
    void testUpdateCarSuccess() {
        Car car = new Car();
        car.setCarId("101");
        car.setCarName("Audi");
        car.setCarColor("Silver");
        car.setCarQuantity(2);
        carRepository.create(car);

        car.setCarName("Porsche");
        car.setCarColor("Black");
        car.setCarQuantity(3);

        Car result = carRepository.update("101", car);

        assertNotNull(result);
        assertEquals("101", result.getCarId());
        assertEquals("Porsche", result.getCarName());
        assertEquals("Black", result.getCarColor());
        assertEquals(3, result.getCarQuantity());

        Car fetchedCar = carRepository.findById("101");
        assertNotNull(fetchedCar);
        assertEquals("Porsche", fetchedCar.getCarName());
        assertEquals("Black", fetchedCar.getCarColor());
        assertEquals(3, fetchedCar.getCarQuantity());
    }


    @Test
    void testUpdateCarNotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarId("999");
        updatedCar.setCarName("Ford");

        Car result = carRepository.update("999", updatedCar);

        assertNull(result);
    }

    @Test
    void testDeleteCarSuccess() {
        Car car = new Car();
        car.setCarId("202");
        car.setCarName("Ferrari");

        carRepository.create(car);
        assertEquals(1, ((List<Car>) carRepository.findAll()).size());

        carRepository.delete("202");
        assertEquals(0, ((List<Car>) carRepository.findAll()).size());
    }

    @Test
    void testDeleteCarNotFound() {
        Car car = new Car();
        car.setCarId("303");
        car.setCarName("Lamborghini");

        carRepository.create(car);
        assertEquals(1, ((List<Car>) carRepository.findAll()).size());

        carRepository.delete("404");
        assertEquals(1, ((List<Car>) carRepository.findAll()).size());
    }
}
