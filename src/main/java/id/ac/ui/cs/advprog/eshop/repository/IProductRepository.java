package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

public interface IProductRepository extends ReadOnlyRepository<Product, String>, WriteRepository<Product, String> {
}