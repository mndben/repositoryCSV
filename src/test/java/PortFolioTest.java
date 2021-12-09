import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PortFolioTest {

    PortFolio portFolio = new PortFolio();

    HashMap<String,ProductDetail> productDetailsObjectList = new HashMap<>();
    Client client = new Client();
    String filepath = "./src/main/resources/Prices.csv";



    @Test
    void getName() {
        portFolio.setName("pft1");
        assertEquals("pft1", portFolio.getName());
    }

    @Test
    void setName() {

        portFolio.setName("pft1");
        assertEquals("pft1", portFolio.getName());
    }

    @Test
    void getProducts() {
    }

    @Test
    void setProducts() {
    }

    @Test
    void getPrice() {

        portFolio.setPrice(10.00);
        assertEquals(10.00, portFolio.getPrice());
    }

    @Test
    void setPrice() {
        portFolio.setPrice(10.00);
        assertEquals(10.00, portFolio.getPrice());
    }

    @Test
    void portfolioDetails() {


        ProductDetail product = new ProductDetail();
        product.processPortfolioProduct(CsvIO.LoadCsv(filepath));
        product.productCount();

        productDetailsObjectList = product.getProductDetailsObjectList();
        HashMap<String, Double> capitalList = new HashMap<>();

        capitalList = client.clientCapital(productDetailsObjectList);

        ArrayList<PortFolio> porFolioObjectArrayList = portFolio.portfolioDetails(product.getPortFolioNameList(), product.getProductDetailsObjectList(),product.getProductCount());

        //test
        assertEquals(29116.00, porFolioObjectArrayList.get(0).getPrice());
    }
}