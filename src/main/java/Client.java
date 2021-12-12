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


}
