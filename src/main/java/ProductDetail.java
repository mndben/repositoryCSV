import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetail {

    //constructor variables declarations
    private String portfolio;
    private Double price;
    private HashMap<String, Integer> productCount = new HashMap<>();

    //local variables
    private  ArrayList<String> portFolioNameList;
    private  HashMap<String,ProductDetail> productDetailsObjectList;

    //constructor method
    public ProductDetail(String productPortfolio, Double productPrice){

        portfolio = productPortfolio;
        price = productPrice;

    }

    //empty constructor


    public ProductDetail() {
    }

    //getter methods
    public String getPortfolio() {
        return portfolio;
    }
    public Double getPrice() {

        return price;
    }
    public HashMap<String, ProductDetail> getProductDetailsObjectList() {
        return productDetailsObjectList;
    }
    public ArrayList<String> getPortFolioNameList() {
        return portFolioNameList;
    }

    //a method for computing price of products in euro
    public void processPortfolioProduct(ArrayList<String[]> rawProduct){

        //product info
        //ArrayList<HashMap<String, ProductDetail>> productInfo = new ArrayList<>();
        //temp hashmap for storing product info
        HashMap<String, ProductDetail> tempProductMap = new HashMap<>();
        //constructor for portfolio details
        ArrayList<PortFolio> portFolio = new ArrayList<>();

        //list of portFolio
        ArrayList<String> portFolioList = new ArrayList<>();



        //loop through the arraylist of product csv
        for (int i = 0; i < rawProduct.size(); i++) {

            //check to see if the current column is valid
            if(rawProduct.get(i).length == 5){
                //initialize temp product info array
                String[] tempProductInfo = rawProduct.get(i);
                //check if the current column is not a header

                if(!tempProductInfo[0].toLowerCase().equals("Portfolio".toLowerCase())){

                    //get current product name and other required information
                    String tempProductName = tempProductInfo[1].toLowerCase();
                    Double tempPrice = Utility.parseFloatOrNull(tempProductInfo[4]);
                    //convert currency if necessary
                    if(!tempProductInfo[3].toLowerCase().equals("eur")){
                        tempPrice = Utility.currencyExchange(tempPrice,tempProductInfo[3]);
                    }

                    //other details
                    String tempPortfolioName = tempProductInfo[0];
                    if(!portFolioList.contains(tempPortfolioName.toLowerCase())){
                        //add portfolio name
                        portFolioList.add(tempPortfolioName.toLowerCase());
                    }
                    //current product Total Prirce
                    double tempTotalPrice = 0;

                    //iterate and compute current product total price
                    while (i < rawProduct.size()){

                        //update total price
                        tempTotalPrice+= tempPrice;
                        //update index of the array list
                        i+=1;


                        if(i == rawProduct.size()){
                            break;
                        }

                        //update temp product information
                        tempProductInfo = rawProduct.get(i);
                        tempPrice = Utility.parseFloatOrNull(tempProductInfo[4]);
                        //convert currency if necessary
                        if(!tempProductInfo[3].toLowerCase().equals("eur")){
                            tempPrice = Utility.currencyExchange(tempPrice,tempProductInfo[3]);
                        }

                        if(!tempProductName.toLowerCase().equals(tempProductInfo[1].toLowerCase())){
                            i-=1;
                            break;
                        }
                    }

                    //store current product detail
                    ProductDetail currentProductDetail = new ProductDetail(tempPortfolioName,tempTotalPrice);
                    tempProductMap.put(tempProductName,currentProductDetail);
                    //productInfo.add(tempProductMap);
                }
            }
        }

        //set main clas with result
        this.productDetailsObjectList = tempProductMap;
        this.portFolioNameList = portFolioList;
    }


    //a method for computing product count for each products
    public HashMap<String, Integer> productCount(HashMap<String,ProductDetail> productDetailsObjectList){

        //path to csv
        String filepath = "./src/main/resources/Product.csv";

        //load client product detail
        ArrayList<String[]> productCountRaw = CsvIO.LoadCsv(filepath);

        //temp hashmap for storing client info
        HashMap<String, Double> tempClientMap = new HashMap<>();
        //constructor for list of clients detail
        //ArrayList<Client> clients = new ArrayList<>();

        //loop through the arraylist of product csv
        for (int i = 0; i < productCountRaw.size(); i++) {

            //check to see if the current column is valid
            if(productCountRaw.get(i).length == 3){
                //initialize temp product info array
                String[] tempProductInfo = productCountRaw.get(i);
                //check if the current column is not a header

                if(!tempProductInfo[0].toLowerCase().equals("Product".toLowerCase())) {

                    //initialize client detail variables
                    String clientName = tempProductInfo[1].toLowerCase();
                    String productName = tempProductInfo[0].toLowerCase();
                    Integer productCount = Utility.parseIntOrNull(tempProductInfo[2]);

                    //check if the product count is valid value
                    if (productCount == -1) {
                        //tell user invalid product count
                        System.out.println("Invalid Product Count For :" + productName);
                        continue;
                    }
                    //update product count
                    if (this.productCount.containsKey(productName)) {
                        this.productCount.put(productName, this.productCount.get(productName) + productCount);
                    } else {
                        this.productCount.put(productName, productCount);
                    }
                }
            }
        }
        return this.productCount;
    }


}
