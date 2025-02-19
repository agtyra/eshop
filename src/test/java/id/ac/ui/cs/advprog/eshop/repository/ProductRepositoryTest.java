package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        // Clear repository before each test
        productRepository = new ProductRepository();
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Soap");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sabun");
        updatedProduct.setProductQuantity(34);

        Product result = productRepository.edit(updatedProduct);

        assertNotNull(result);
        assertEquals("Sabun", result.getProductName());
        assertEquals(34, result.getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63kms");
        updatedProduct.setProductName("Napkin");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.edit(updatedProduct);

        assertNull(result);
    }

    @Test
    void testDeleteProductSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bl9");
        product.setProductName("Sampo Cap Ben");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bl9");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId("ej438e9f-1c39-460e-8860-71af6af63gf4");
        product.setProductName("Lotion");
        product.setProductQuantity(50);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63gf4");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testCreateProductWithoutId() {
        Product product = new Product();
        product.setProductName("Shampoo Without ID");
        product.setProductQuantity(100);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId(), "Product ID should not be null");
        assertFalse(createdProduct.getProductId().isEmpty(), "Product ID should not be empty");
    }

    @Test
    void testCreateProductWithEmptyId() {
        Product product = new Product();
        product.setProductId("");  // Empty string instead of null
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId(), "Product ID should not be null");
        assertFalse(createdProduct.getProductId().isEmpty(), "Product ID should not be empty");
    }


}

