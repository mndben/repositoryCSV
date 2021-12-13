import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PortFolioTest {





    @Mock Product product;
    @Mock PortFolio portFolio;

    String filePathProduct = "./src/main/resources/Product.csv";
    String filePath = "./src/main/resources/Forex.csv";
    String filePathPrice = "./src/main/resources/Prices.csv";
    ArrayList<String[]> rawForexData;
    HashMap<String, Double> testPorFolioDetails;


    @BeforeEach
    void setUp() {
        product = new Product();
        portFolio = new PortFolio("portfolioZ", null, null);

        testPorFolioDetails = new HashMap<>();
        testPorFolioDetails.put("ptf2",155700.00);
        testPorFolioDetails.put("ptf1",29116.00);

        rawForexData = Item.loadCsv(filePath);
        product.setConversionRate(rawForexData);
        product.processRawItem(Item.loadCsv(filePathPrice),product,portFolio);

    }

    @Test
    void processPortfolioDetails() {

        /* collect actual portfolio details map object */
        HashMap<String, Double> portfolioDetails =
                portFolio.processPortfolioDetails(
                        portFolio.getportFolioNameList(),
                        product.getProductList(),product.
                                productCount(Item.loadCsv(filePathProduct)));

        /* Test : check if portfolio price is correct*/
        for (String key:
                testPorFolioDetails.keySet()) {

            assertEquals(testPorFolioDetails.get(key), portfolioDetails.get(key));

        }


    }

    @Test
    void getName() {
        /* Test : testing getter method */
        assertEquals("portfolioZ", portFolio.getName());
    }

    @Test
    void getPrice() {
        /* Test : testing setter and  getter method for price*/

        portFolio.setPrice(100.00);

        /* Test: get price */
        assertEquals(100.00,portFolio.getPrice());
    }

}