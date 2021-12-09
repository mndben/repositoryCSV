import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CsvIOTest {

    String filepath = "./src/main/resources/";
    CsvIO csvio = new CsvIO();
    ArrayList<String> products = new ArrayList<>();



    PortFolio portFolio = new PortFolio();

    ArrayList<PortFolio> portFolioList = new ArrayList<>();
    HashMap<String, Double> clientCapitalMap = new HashMap<>();

    @Test
    void writeToCsv() {
        assertDoesNotThrow(() -> {csvio.writeToCsv(new String[]{"1","2"},filepath+"a.csv");});
    }

    @Test
    void writeResultToCsv() {

        products.add("p1");
        products.add("p2");
        products.add("p3");

        portFolio.setProducts(products);
        portFolio.setName("pft1");
        portFolio.setPrice(2400.00);

        portFolioList.add(portFolio);

        clientCapitalMap.put("c1", 2500.00);
        clientCapitalMap.put("c2", 2500.00);



        assertDoesNotThrow(() -> {csvio.writeResultToCsv(portFolioList,clientCapitalMap);});

    }

    @Test
    void loadCsv() {

        assertDoesNotThrow(() -> {CsvIO.LoadCsv(filepath+"Forex.csv");});
        assertDoesNotThrow(() -> {CsvIO.LoadCsv(filepath+"Product.csv");});
        assertDoesNotThrow(() -> {CsvIO.LoadCsv(filepath+"Prices.csv");});
    }


}