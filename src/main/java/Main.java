import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    //constructor
    public Main() {

    }

    //main method
    public static void main(String[] args) {
        // write your code here

        /* root file path and load raw data */
        String rootPath = "./src/main/resources/";
        ArrayList<String[]> rawPricesInfo = Item.loadCsv(rootPath+"Prices.csv");
        ArrayList<String[]> rawProductsInfo = Item.loadCsv(rootPath+"Product.csv");
        ArrayList<String[]> rawForexInfo = Item.loadCsv(rootPath+"Forex.csv");
        /* end of root file path and loading raw data*/

        /*object instance */
        Client client = new Client();
        Product product  = new Product();
        PortFolio portFolio = new PortFolio();
        /*end of object instance */

        /* main method body */
        //set conversion rate
        product.setConversionRate(rawForexInfo);
        //get client capital
        product.processRawItem(rawPricesInfo,product,portFolio);

        //for test
        //System.out.println(product.getProductDetailsObjectList().get("p1").getPortfolio());
        HashMap<String, Double> clientCapitalList = client.processClientCapital(product.getProductList(),rawProductsInfo);

        //for test
        //System.out.println(clientCapitalList.toString());

        //get product count
        HashMap<String, Integer> productCountList = product.productCount(rawProductsInfo);

        //get portfolio capital
        HashMap<String, Double> portFolioCapitalList = portFolio.processPortfolioDetails(portFolio.getportFolioNameList(), product.getProductList(), productCountList);

        //create the two csv files
        portFolio.writeResultToCsv(portFolioCapitalList,rootPath+"result/Reporting-portfolio.csv");
        client.writeResultToCsv(clientCapitalList,rootPath+"result/Reporting-client.csv");
    }
}
