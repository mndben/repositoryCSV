import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {


    @Mock Item item;
    @Mock IOException ex;
    @Mock Product product;
    @Mock PortFolio portFolio;

    ArrayList<String[]> rawForexData;
    HashMap<String, String> testProductList;
    String filePathForex = "./src/main/resources/Forex.csv";
    String filePathPrice = "./src/main/resources/Prices.csv";
    ArrayList<String[]> rawPricesInfo;
    HashMap<String,Double> exchangeRate;
    HashMap<String,Double> productPriceList;
    ArrayList<String> testPortfolioList;
    IOException ioe;


    @BeforeEach
    void setUp() {
        product = new Product();
        portFolio = new PortFolio();
        testProductList = new HashMap<>();
        productPriceList = new HashMap<>();
        testPortfolioList = new ArrayList<>();

        //add expected item to test portfolio list
        testPortfolioList.add("ptf1");
        testPortfolioList.add("ptf2");

        //add expected item to test products list
        testProductList.put("p1","PTF1");
        testProductList.put("p2","PTF1");
        testProductList.put("p3","PTF1");
        testProductList.put("x1","PTF2");
        testProductList.put("x2","PTF2");

        //add expected item to test products price list
        productPriceList.put("p1",35.0);
        productPriceList.put("p2",0.5);
        productPriceList.put("p3",83.6);
        productPriceList.put("x1",30.0);
        productPriceList.put("x2",960.0);

        rawForexData = Item.loadCsv(filePathForex);
        product.setConversionRate(rawForexData);
        exchangeRate = product.getConversionRate();
        rawPricesInfo = Item.loadCsv(filePathPrice);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("load csv data")
    void loadCsv() {
        String filePath = "./src/main/resources/Product.csv";

        /* Test : expected return type */
        assertInstanceOf(ArrayList.class, Item.loadCsv(filePath));

        /*Test : returned parsed csv */
        assertTrue(Item.loadCsv(filePath).size() > 0);
    }

    @Test @DisplayName("test for parseIntOrNull , and includes Test for parseFloatOrNull")
    void parseIntOrNull() {


        /* Test - returned int and double casting  */
        assertAll(() -> assertEquals(5, Item.parseIntOrNull("5")),
                () -> assertEquals(2, Item.parseIntOrNull("2")),
                () -> assertEquals(-1, Item.parseIntOrNull("3.0#adfa")),
                () -> assertEquals(-1, Item.parseIntOrNull("-1")));
        /* double casting */
        assertAll(() -> assertEquals(5, Item.parseFloatOrNull("5.0")),
                () -> assertEquals(2, Item.parseFloatOrNull("2.0")),
                () -> assertEquals(-1, Item.parseFloatOrNull("3afa")),
                () -> assertEquals(-1, Item.parseFloatOrNull("-1.0")));

        /* Test - with invalid int and double value  */
        assertDoesNotThrow(() -> { Item.parseIntOrNull("invalid"); });

    }

    @Test @DisplayName("Currency Convertor")
    void currencyExchange() {
        /* Test - check currency exchange calculation result  */
        assertAll(() -> assertEquals(10.00 * 0.5, product.currencyExchange(10.00,"usd",exchangeRate)),
                () -> assertEquals(5.00 / 50.00, product.currencyExchange(5.00,"jpy",exchangeRate)),
                () -> assertEquals(20.00 * 4, product.currencyExchange(20.00,"chf",exchangeRate)),
                () -> assertEquals(1.00 * 8, product.currencyExchange(1.00,"gpb",exchangeRate)));
    }

    @Test @DisplayName("set product and portfolio item detail")
    void processRawItem() {

        /* testing product and portfolio object content from processRawItem method */
        product.processRawItem(rawPricesInfo,product,portFolio);

        /* test - product portfolio */
        ArrayList<Product> productList = product.getProductList();
        for (String key:
                testProductList.keySet()) {

            for (int i = 0; i < productList.size(); i++) {

                if(productList.get(i).getName().equalsIgnoreCase(key)){

                    /* test if portfolio is correctly assigned */
                    assertEquals(testProductList.get(key), productList.get(i).getPortfolio());

                    /* test if product price is correctly set */
                    assertEquals(productPriceList.get(key), productList.get(i).getPrice());
                }

            }

        }
        /* test - check portfolio list if it contains all the expected portfolios */
        ArrayList<String> portfolioNameList = portFolio.getportFolioNameList();
        for (int i = 0; i < testPortfolioList.size(); i++) {

            assertTrue(portfolioNameList.contains(testPortfolioList.get(i)));
        }

        /* test - check product price is correct after including currency conversion */





    }

    @Test
    void writeResultToCsv() {
        String testPath = "./src/main/resources/result/test.csv";

        /* Test : expected error through if invalid path*/
        assertDoesNotThrow(() -> {Item.writeToCsv(new String[]{"item1", "item2"},testPath);});
    }

    @Test
    void writeToCsv() {

        String testPath = "./src/main/resources/result/test.csv";

        /* Test : expected error IOexceptio */
        assertDoesNotThrow(() -> {
            Item.writeToCsv(new String[]{"item1","item2"}, testPath);
        }, "NullPointerException was expected");

    }
}