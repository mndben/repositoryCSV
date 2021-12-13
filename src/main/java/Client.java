import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Item{

    private String name;
    private Double price;
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

    /* getter and setter methods */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    /* getter and setter method end */

    //a method for computing client capital
    public HashMap<String, Double> processClientCapital(ArrayList<Product> productList, ArrayList<String[]> rawProductData){

        //temp hashmap for storing client info
        HashMap<String, Double> tempClientMap = new HashMap<>();
        //constructor for list of clients detail
        //ArrayList<Client> clients = new ArrayList<>();

        //loop through the arraylist of product csv
        for (int i = 0; i < rawProductData.size(); i++) {

            //check to see if the current column is valid
            if(rawProductData.get(i).length == 3){
                //initialize temp product info array
                String[] tempClientInfo = rawProductData.get(i);
                //check if the current column is not a header

                if(!tempClientInfo[0].toLowerCase().equals("Product".toLowerCase())){

                    //initialize client detail variables
                    String clientName = tempClientInfo[1].toLowerCase();
                    String productName = tempClientInfo[0].toLowerCase();
                    Integer productCount = parseIntOrNull(tempClientInfo[2]);

                    //check if the product count is valid value
                    if(productCount == -1){
                        //tell user invalid product count
                        System.out.println("Invalid Product Count For :"+productName);
                        continue;
                    }
                    //product price
                    Double productPrice = 0.00;
                    //check to see if the list contains info for the current product name
                    for (int j = 0; j < productList.size(); j++) {

                        Product tempProduct = productList.get(j);
                        if(tempProduct.getName().equalsIgnoreCase(productName)){
                            productPrice = tempProduct.getPrice();
                        }
                    }
                    //store or update client info
                    if(tempClientMap.containsKey(clientName)){
                        tempClientMap.put(clientName, tempClientMap.get(clientName) + (productPrice*productCount));
                    }else{
                        tempClientMap.put(clientName, (productPrice*productCount));
                    }
                    System.out.println(clientName + ":" + productPrice*productCount);
                }
            }
        }
        return tempClientMap;
    }


}
