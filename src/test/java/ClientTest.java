import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {


    HashMap<String,ProductDetail> productDetailsObjectList = new HashMap<>();
    Client client = new Client();
    String filepath = "./src/main/resources/Prices.csv";


    @Test
    void clientCapital() {

        ProductDetail product = new ProductDetail();
        product.processPortfolioProduct(CsvIO.LoadCsv(filepath));

        productDetailsObjectList = product.getProductDetailsObjectList();
        HashMap<String, Double> capitalList = new HashMap<>();

        capitalList = client.clientCapital(productDetailsObjectList);

        //test samples
        //ProductDetail productDetailTest = new ProductDetail("PTF1", 35.00);

        assertEquals(7738.0, capitalList.get("c3"));
        assertEquals(350.0, capitalList.get("c4"));
        assertEquals(55534.0, capitalList.get("c5"));
        assertEquals(2808.0, capitalList.get("c6"));

    }

    @Test
    void getName() {
        Client clientTest = new Client("c3" , null);
        clientTest.setCapital(12.00);
        assertEquals("c3", clientTest.getName());
    }

    @Test
    void getCapital() {
        Client clientTest = new Client("c3" , null);
        clientTest.setCapital(12.00);
        assertEquals(12.00, clientTest.getCapital());
    }

    @Test
    void getInventory() {
        Client clientTest = new Client("c3" , null);
        assertEquals(null, client.getInventory());
    }

    @Test
    void setName() {
        client.setName("c10");
        assertEquals("c10", client.getName());
    }

    @Test
    void setCapital() {
        client.setCapital(100.00);
        assertEquals(100.00, client.getCapital());
    }

    @Test
    void setInventory() {

        HashMap<String,Integer> invertoryTest = new HashMap<>();
        invertoryTest.put("p1", 40);
        invertoryTest.put("p2", 20);
        client.setInventory(invertoryTest);
        assertEquals(40, client.getInventory().get("p1"));
    }
}