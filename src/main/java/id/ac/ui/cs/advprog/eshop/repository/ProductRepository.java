package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements IProductRepository {
    private final List<Product> productData = new ArrayList<>();

    @Override
    public Iterable<Product> findAll() { return productData; }

    @Override
    public Product findById(String id) {
        return productData.stream().filter(product -> product.getProductId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    @Override
    public Product update(String id, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}

