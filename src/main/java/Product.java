import java.util.ArrayList;
import java.util.HashMap;

public class Product extends Item{

    //constructor variables declarations
    private String portfolio;
    private String name;
    private Double price;

    private HashMap<String, Double> conversionRate;
    ArrayList<Product> productList = new ArrayList<>();
    //constructor method
    public Product(String name, String portfolio, Double price){

        this.portfolio = portfolio;
        this.price = price;
        this.name = name;
    }

    public Product(){}

    /* Setter and getter methods */

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public HashMap<String, Double> getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(ArrayList<String[]> forexRawData) {
        this.conversionRate = computeProductPriceConversionRate(forexRawData);
    }

    /* getter and setter method from abstract class*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    /* end of getter and setter method from abstract class */

    //process conversion rate from csv data
    public HashMap<String, Double> computeProductPriceConversionRate(ArrayList<String[]> rawForexData) {
        //temp conversion rate storage
        HashMap<String, Double> conversionList = new HashMap<>();
        //iterate through the files to extract exchange rate to euro
        for (int i = 0; i < rawForexData.size(); i++) {

            //check to see if the current column is valid
            if(rawForexData.get(i).length == 3){
                //initialize temp product info array
                String[] tempForexInfo = rawForexData.get(i);
                //check if the current column is not a header
                if(!tempForexInfo[0].toLowerCase().equals("Currency".toLowerCase())){

                    String fromCurrency = tempForexInfo[0];
                    String toCurrency = tempForexInfo[1];
                    Double xChangeRate = parseFloatOrNull(tempForexInfo[2]);

                    if(fromCurrency.toLowerCase().equals("eur")){
                        conversionList.put(toCurrency.toLowerCase(), 1/xChangeRate);

                    }else{
                        conversionList.put(fromCurrency.toLowerCase(), xChangeRate);
                    }
                }
            }
        }
        return conversionList;
    }

    public HashMap<String, Integer> productCount(ArrayList<String[]> rawProductInfo){

        //temp hashmap for storing product count
        HashMap<String, Integer> productCounts = new HashMap<>();

        //loop through the arraylist of product csv rawdata
        for (int i = 0; i < rawProductInfo.size(); i++) {

            //check to see if the current column is valid
            if(rawProductInfo.get(i).length == 3){
                //initialize temp product info array
                String[] tempProductInfo = rawProductInfo.get(i);

                //check if the current column is not a header
                if(!tempProductInfo[0].toLowerCase().equals("Product".toLowerCase())) {

                    //initialize client detail variables
                    String productName = tempProductInfo[0].toLowerCase();
                    Integer productCount = parseIntOrNull(tempProductInfo[2]);

                    //check if the product count is valid value
                    if (productCount == -1) {
                        //tell user invalid product count
                        System.out.println("Invalid Product Count For :" + productName);
                        continue;
                    }
                    //update product count
                    if (productCounts.containsKey(productName)) {
                        productCounts.put(productName, productCounts.get(productName) + productCount);
                    } else {
                        productCounts.put(productName, productCount);
                    }
                }
            }
        }
        return productCounts;
    }

}
