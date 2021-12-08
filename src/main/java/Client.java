import java.util.ArrayList;
import java.util.HashMap;

public class Client {

    private String name;
    private Double capital;
    private HashMap<String,Integer> inventory;

    //product count for each product
    private HashMap<String, Integer> productCount = new HashMap<>();

    public Client(String name, HashMap<String, Integer> inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    //empty constructor
    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    //a method for computing client capital
    public HashMap<String, Double> clientCapital(HashMap<String,ProductDetail> productDetailsObjectList){

        //path to csv
        String filepath = "./src/main/resources/Product.csv";

        //load client product detail
        ArrayList<String[]> clientCapitalRaw = CsvIO.LoadCsv(filepath);

        //temp hashmap for storing client info
        HashMap<String, Double> tempClientMap = new HashMap<>();
        //constructor for list of clients detail
        //ArrayList<Client> clients = new ArrayList<>();

        //loop through the arraylist of product csv
        for (int i = 0; i < clientCapitalRaw.size(); i++) {

            //check to see if the current column is valid
            if(clientCapitalRaw.get(i).length == 3){
                //initialize temp product info array
                String[] tempClientInfo = clientCapitalRaw.get(i);
                //check if the current column is not a header

                if(!tempClientInfo[0].toLowerCase().equals("Product".toLowerCase())){

                    //initialize client detail variables
                    String clientName = tempClientInfo[1].toLowerCase();
                    String productName = tempClientInfo[0].toLowerCase();
                    Integer productCount = Utility.parseIntOrNull(tempClientInfo[2]);

                    //check if the product count is valid value
                    if(productCount == -1){
                        //tell user invalid product count
                        System.out.println("Invalid Product Count For :"+productName);
                        continue;
                    }
                    //product price
                    Double productPrice = 0.00;
                    //check to see if the list contains info for the current product name
                    if(productDetailsObjectList.containsKey(productName)){
                        productPrice = productDetailsObjectList.get(productName).getPrice();
                    }else{
                        //tell user invalid product
                        System.out.println("Product Name not Available in the products List, \n " +
                                "Product Name :" + productName);
                        continue;
                    }



                    //store or update client info
                    if(tempClientMap.containsKey(clientName)){
                        tempClientMap.put(clientName, tempClientMap.get(clientName) + (productPrice*productCount));
                    }else{
                        tempClientMap.put(clientName, (productPrice*productCount));
                    }
                    //check code
                    /*if(clientName.equals("c3")){
                        System.out.println("name: "+productName + "price: " +productPrice);
                        System.out.println("productCount"+ productCount);
                        System.out.println("result"+  productPrice*productCount);

                    }*/
                }
            }
        }

        //see results
        return tempClientMap;
    }
}
