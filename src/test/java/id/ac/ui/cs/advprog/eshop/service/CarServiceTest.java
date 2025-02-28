package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.ICarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private ICarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car sampleCar;

    @BeforeEach
    void setUp() {
        sampleCar = new Car();
        sampleCar.setCarId("1");
        sampleCar.setCarName("Sample Car");
    }

    @Test
    void testCreateCar() {
        when(carRepository.create(any(Car.class))).thenReturn(sampleCar);

        Car createdCar = carService.create(sampleCar);

        assertNotNull(createdCar);
        assertEquals("1", createdCar.getCarId());
        verify(carRepository, times(1)).create(any(Car.class));
    }

    @Test
    void testFindAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(sampleCar);

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = carService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getCarId());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindCarById() {
        when(carRepository.findById("1")).thenReturn(sampleCar);

        Car foundCar = carService.findById("1");

        assertNotNull(foundCar);
        assertEquals("1", foundCar.getCarId());
        verify(carRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateCar() {
        carService.update("1", sampleCar);
        verify(carRepository, times(1)).update(anyString(), any(Car.class));
    }

    @Test
    void testDeleteCarById() {
        doNothing().when(carRepository).delete(anyString());

        assertDoesNotThrow(() -> carService.deleteCarById("1"));
        verify(carRepository, times(1)).delete("1");
    }
}
