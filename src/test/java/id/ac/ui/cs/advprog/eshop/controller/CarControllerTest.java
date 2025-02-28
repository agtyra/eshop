package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareConcurrentModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareConcurrentModel();
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);

        assertEquals("createCar", viewName);
        assertInstanceOf(Car.class, model.getAttribute("car"));

    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        car.setCarId("1");

        String redirect = carController.createCarPost(car);

        verify(carService, times(1)).create(car);
        assertEquals("redirect:listCar", redirect);
    }

    @Test
    void testCarListPage() {
        List<Car> carList = Arrays.asList(new Car(), new Car());
        when(carService.findAll()).thenReturn(carList);

        String viewName = carController.carListPage(model);

        assertEquals("carList", viewName);
        assertEquals(carList, model.getAttribute("cars"));
    }

    @Test
    void testEditCarPage() {
        Car car = new Car();
        car.setCarId("1");
        when(carService.findById("1")).thenReturn(car);

        String viewName = carController.editCarPage("1", model);

        assertEquals("editCar", viewName);
        assertEquals(car, model.getAttribute("car"));
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("1");

        String redirect = carController.editCarPost(car);

        verify(carService, times(1)).update("1", car);
        assertEquals("redirect:listCar", redirect);
    }

    @Test
    void testDeleteCar() {
        String redirect = carController.deleteCar("1");

        verify(carService, times(1)).deleteCarById("1");
        assertEquals("redirect:listCar", redirect);
    }
}
