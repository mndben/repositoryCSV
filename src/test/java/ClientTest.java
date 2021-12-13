import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Mock
    Product product;
    @Mock Client client;
    @Mock PortFolio portFolio;

    String filePathForex = "./src/main/resources/Forex.csv";
    String filePathProduct = "./src/main/resources/Product.csv";
    String filePathPrice = "./src/main/resources/Prices.csv";
    ArrayList<String[]> rawForexData;
    ArrayList<String[]> rawProductData;
    HashMap<String, Double> clientCapitalList;

    @BeforeEach
    void setUp() {
        product = new Product();
        client = new Client();
        portFolio = new PortFolio();

        rawForexData = Item.loadCsv(filePathForex);
        rawProductData = Item.loadCsv(filePathProduct);

        product.setConversionRate(rawForexData);
        product.setConversionRate(rawForexData);

        product.processRawItem(Item.loadCsv(filePathPrice),product,portFolio);

        //add expected item to test client capital
        clientCapitalList =new HashMap<>();
        clientCapitalList.put("c1",990.00);
        clientCapitalList.put("c2",28960.00);
        clientCapitalList.put("c3",7738.00);
        clientCapitalList.put("c4",350.00);
        clientCapitalList.put("c5",55534.00);

    }

    @Test
    void processClientCapital() {

        /* Test : the return type is the right object */
        assertInstanceOf(HashMap.class, client.processClientCapital(product.getProductList(),rawProductData));

        //get the return value map
        HashMap<String, Double> tempClientMap = client.processClientCapital(product.getProductList(),rawProductData);

        /* Test : client capital is correctly calculated and included */
        for (String key:
                clientCapitalList.keySet()) {
            assertEquals(clientCapitalList.get(key), tempClientMap.get(key));

        }






    }
}