import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

//local imports


public class Main {

    //constructor
    public Main() {

    }

    //main method
    public static void main(String[] args) {
        // write your code here

        //object instance
        Client client = new Client();
        ProductDetail product  = new ProductDetail();
        PortFolio portFolio = new PortFolio();
        CsvIO csvIO = new CsvIO();


        Main mainC = new Main();
        // specify file path to csv
        String filepath = "./src/main/resources/Prices.csv";
        //call to open csv method

        //get client capital
        product.processPortfolioProduct(CsvIO.LoadCsv(filepath));
        HashMap<String, Double> clientCapitalList = client.clientCapital(product.getProductDetailsObjectList());

        //get product count
        HashMap<String, Integer> productCountList = product.productCount();

        //get portfolio capital
        ArrayList<PortFolio> portFolioCapitalList = portFolio.portfolioDetails(product.getPortFolioNameList(), product.getProductDetailsObjectList(), productCountList);

        //create the two csv files
        csvIO.writeResultToCsv(portFolioCapitalList, clientCapitalList);
    }
}
