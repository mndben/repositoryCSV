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
    String filePathProduct = "./src/main/resources/Product.csv";
    HashMap<String, Integer> productCountList;

    @BeforeEach
    void setUp() {
        product = new Product();
        rawForexData = Item.loadCsv(filePath);
        productCountList = new HashMap<>();

        productCountList.put("p1",90);
        productCountList.put("p2",100);
        productCountList.put("p3",310);
        productCountList.put("x1",70);
        productCountList.put("x2",160);

    }

    @AfterEach
    void tearDown() {
    }
    @DisplayName("product conversion rate calculator")
    @Test
    void computeProductPriceConversionRateTest() {
        HashMap<String,Double> exchangeRate = product.computeProductPriceConversionRate(rawForexData);
        /* Test : expected currency rate */
        assertAll(() -> assertEquals(0.5, exchangeRate.get("usd")),
                () -> assertEquals(4.0, exchangeRate.get("chf")),
                () -> assertEquals(1.00/50.00, exchangeRate.get("jpy")),
                () -> assertEquals(8.0, exchangeRate.get("gpb")));
    }

    @Test
    void productCount(){

        /* Test : product count is correct */
        HashMap<String,Integer> productCountActualList = product.productCount(Item.loadCsv(filePathProduct));
        for (String key:
                productCountList.keySet()) {

            assertEquals(productCountList.get(key), productCountActualList.get(key.toLowerCase()));

        }


    }

}