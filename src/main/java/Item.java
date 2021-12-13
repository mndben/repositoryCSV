import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

abstract class Item {

    String name;
    Double price;

    //double formatter
    public static final DecimalFormat df = new DecimalFormat("0.00");


    /* abstract getter and setter */
    public abstract String getName();

    public abstract void setName(String name);

    public abstract Double getPrice();

    public abstract void setPrice(Double price);
    /* abstract getter and setter */


    //a method for converting string to int
    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //a method for converting string to double
    public static Double parseFloatOrNull(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1.00;
        }
    }

    //a method for loading csv files
    public static ArrayList<String[]> loadCsv(String fileName){

        //initialize an array list for holding loaded data
        ArrayList<String[]> data = new ArrayList<>();

        // create a Buffer reader object
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            // CSV file delimiter
            String DELIMITER = ",";

            // read the file line by line
            String line;
            while ((line = br.readLine()) != null) {

                // convert line into columns
                String[] columns = line.split(DELIMITER);

                //store current iteration data to the arraylist
                data.add(columns);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;

    }

    //a method for preparing exchange rate from any price to euro
    public double currencyExchange(Double fromCurrencyPrice, String currencyName, HashMap<String, Double> conversionList){

        return conversionList.get(currencyName.toLowerCase()) * fromCurrencyPrice;

    }

    //a method for computing price of products in euro
    public void processRawItem(ArrayList<String[]> rawItemInfo, Product product,PortFolio portFolio){
        //temp hashmap for storing product info
        ArrayList<Product> productList = new ArrayList<>();

        //constructor for portfolio details
        //ArrayList<PortFolio> portFolioList = new ArrayList<>();

        //list of portFolio
        ArrayList<String> portFolioNameList = new ArrayList<>();



        //loop through the arraylist of product csv
        for (int i = 0; i < rawItemInfo.size(); i++) {

            //check to see if the current column is valid
            if(rawItemInfo.get(i).length == 5){
                //initialize temp product info array
                String[] tempProductInfo = rawItemInfo.get(i);
                //check if the current column is not a header

                if(!tempProductInfo[0].toLowerCase().equals("Portfolio".toLowerCase())){

                    //get current product name and other required information
                    String tempProductName = tempProductInfo[1].toLowerCase();
                    Double tempPrice = parseFloatOrNull(tempProductInfo[4]);
                    //convert currency if necessary
                    if(!tempProductInfo[3].toLowerCase().equals("eur")){
                        tempPrice = currencyExchange(tempPrice,tempProductInfo[3], product.getConversionRate());
                    }

                    //other details
                    String tempPortfolioName = tempProductInfo[0];
                    if(!portFolioNameList.contains(tempPortfolioName.toLowerCase())){
                        //add portfolio name
                        portFolioNameList.add(tempPortfolioName.toLowerCase());
                    }
                    //current product Total Prirce
                    double tempTotalPrice = 0;

                    //iterate and compute current product total price
                    while (i < rawItemInfo.size()){

                        //update total price
                        tempTotalPrice+= tempPrice;
                        //update index of the array list
                        i+=1;

                        if(i == rawItemInfo.size()){
                            break;
                        }

                        //update temp product information
                        tempProductInfo = rawItemInfo.get(i);
                        tempPrice = parseFloatOrNull(tempProductInfo[4]);
                        //convert currency if necessary
                        if(!tempProductInfo[3].toLowerCase().equals("eur")){
                            tempPrice = currencyExchange(tempPrice,tempProductInfo[3],product.getConversionRate());
                        }

                        if(!tempProductName.toLowerCase().equals(tempProductInfo[1].toLowerCase())){
                            i-=1;
                            break;
                        }
                    }
                    //store current product detail
                    Product currentProductDetail = new Product(tempProductName,tempPortfolioName,tempTotalPrice);
                    productList.add(currentProductDetail);
                }
            }
        }
        product.setProductList(productList);
        portFolio.setportFolioNameList(portFolioNameList);
    }

    public void writeResultToCsv(HashMap<String, Double> itemList, String filePath){

        //adding header to csv
        if(filePath.contains("client")){

            writeToCsv(new String[]{"client","price"}, filePath);

        }else{
            writeToCsv(new String[]{"portfolio","price"}, filePath);
        }

        for (String key : itemList.keySet()) {

            String itemName = key;
            Double itemVal = itemList.get(key);

            String[] itemCompressed = {itemName, df.format(itemVal)};
            writeToCsv(itemCompressed, filePath);
        }
    }

    //a method to write a string to csv
    public static void writeToCsv(String[] details, String filePath){

        //initialize file writer class
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //write details
        try {
            fileWriter.append(String.join(",", details));
            fileWriter.append("\n");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



}
