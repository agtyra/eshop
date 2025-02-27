package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface ICarRepository extends ReadOnlyRepository<Car, String>, WriteRepository<Car, String> {
}
