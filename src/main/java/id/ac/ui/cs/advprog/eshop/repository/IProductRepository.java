package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface IProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findProductById(String productId);
    Product edit(Product updatedProduct);
    void delete(String productId);
}
