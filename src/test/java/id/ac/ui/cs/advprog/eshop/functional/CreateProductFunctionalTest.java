package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseurl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void userCanCreateProduct(ChromeDriver driver) {
        // Navigate to create product page
        driver.get(baseUrl + "/product/create");

        // Fill the form
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String testProductName = "Test Product";
        String testProductQuantity = "10";

        nameInput.sendKeys(testProductName);
        quantityInput.sendKeys(testProductQuantity);
        submitButton.click();

        // Navigate to product list page
        driver.get(baseUrl + "/product/list");

        // Verify the product is listed
        List<WebElement> productRows = driver.findElements(By.tagName("tr"));
        boolean productFound = productRows.stream()
                .anyMatch(row -> row.getText().contains(testProductName) && row.getText().contains(testProductQuantity));

        assertTrue(productFound);
    }
}
