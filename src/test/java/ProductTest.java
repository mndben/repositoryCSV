import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Mock Product product;
    @Mock ArrayList<String[]> rawForexData;
    String filePath = "./src/main/resources/Forex.csv";

    @BeforeEach
    void setUp() {
        product = new Product();
        rawForexData = Item.loadCsv(filePath);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("product conversion rate calculater")
    void computeProductPriceConversionRateTest() {
        HashMap<String,Double> exchangeRate = product.computeProductPriceConversionRate(rawForexData);
        /* Test : expected currency rate */
        assertAll(() -> assertEquals(0.5, exchangeRate.get("usd")),
                () -> assertEquals(4.0, exchangeRate.get("chf")),
                () -> assertEquals(1.00/50.00, exchangeRate.get("jpy")),
                () -> assertEquals(8.0, exchangeRate.get("gpb")));
    }

}