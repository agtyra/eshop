package id.ac.ui.cs.advprog.eshop.repository;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterable<Product> products = productRepository.findAll();
        assertTrue(products.iterator().hasNext());
        Product savedProduct = products.iterator().next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterable<Product> products = productRepository.findAll();
        assertFalse(products.iterator().hasNext());
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

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        assertEquals(2, products.size());

        List<String> expectedIds = List.of(product1.getProductId(), product2.getProductId());
        List<String> actualIds = List.of(products.get(0).getProductId(), products.get(1).getProductId());

        assertTrue(expectedIds.containsAll(actualIds) && actualIds.containsAll(expectedIds));
    }


    @Test
    void testUpdateProductSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Soap");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sabun");
        updatedProduct.setProductQuantity(34);

        Product result = productRepository.update(updatedProduct.getProductId(), updatedProduct);
        assertNotNull(result);
        assertEquals("Sabun", result.getProductName());
        assertEquals(34, result.getProductQuantity());
    }

    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63kms");
        updatedProduct.setProductName("Napkin");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.update(updatedProduct.getProductId(), updatedProduct);
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

        Iterable<Product> products = productRepository.findAll();
        assertFalse(products.iterator().hasNext());
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId("ej438e9f-1c39-460e-8860-71af6af63gf4");
        product.setProductName("Lotion");
        product.setProductQuantity(50);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63gf4");

        Iterable<Product> products = productRepository.findAll();
        assertTrue(products.iterator().hasNext());
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
        product.setProductId("");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId(), "Product ID should not be null");
        assertFalse(createdProduct.getProductId().isEmpty(), "Product ID should not be empty");
    }

    @Test
    void testFindByIdNotFound() {
        Product product1 = new Product();
        product1.setProductId("kdr438e9f-1c39-460e-0000-32af6af63gf4");
        product1.setProductName("Garpu");
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("kdr438e9f-424r-460e-0000-32af6af63gf4");
        product2.setProductName("Sendok");
        productRepository.create(product2);

        Product result = productRepository.findById("non-existent-id");

        assertNull(result, "findById should return null when the product is not found.");
    }


}
