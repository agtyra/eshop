package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("1");
        sampleProduct.setProductName("Sample Product");
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(sampleProduct);

        Product createdProduct = productService.create(sampleProduct);

        assertNotNull(createdProduct);
        assertEquals("1", createdProduct.getProductId());
        verify(productRepository, times(1)).create(any(Product.class));
    }

    @Test
    void testFindAll() {
        List<Product> products = new ArrayList<>();
        products.add(sampleProduct);

        Iterator<Product> productIterator = products.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.getFirst().getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        when(productRepository.findProductById("1")).thenReturn(sampleProduct);

        Product foundProduct = productService.findProductById("1");

        assertNotNull(foundProduct);
        assertEquals("1", foundProduct.getProductId());
        verify(productRepository, times(1)).findProductById("1");
    }

    @Test
    void testEditProduct() {
        when(productRepository.edit(any(Product.class))).thenReturn(sampleProduct);

        Product editedProduct = productService.edit(sampleProduct);

        assertNotNull(editedProduct);
        assertEquals("1", editedProduct.getProductId());
        verify(productRepository, times(1)).edit(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");

        assertDoesNotThrow(() -> productService.delete("1"));

        verify(productRepository, times(1)).delete("1");
    }
}
