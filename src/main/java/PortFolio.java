import java.util.ArrayList;
import java.util.HashMap;

public class PortFolio extends Item{

    //constructor variables
    private String name;
    private ArrayList<String> portFolioNameList;
    private Double price;

    //portfolio list
    ArrayList<PortFolio> portFolioList;
    public PortFolio(String name, ArrayList<String> products, Double price) {
        this.name = name;
        this.portFolioNameList = products;
        this.price = price;
    }

    //empty constructor
    public PortFolio() {
    }

    /*getter and setter method */

    public ArrayList<String> getportFolioNameList() {
        return portFolioNameList;
    }

    public void setportFolioNameList(ArrayList<String> portFolioNameList) {
        this.portFolioNameList = portFolioNameList;
    }

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

    public ArrayList<PortFolio> getPortFolioList() {
        return portFolioList;
    }

    public void setPortFolioList(ArrayList<PortFolio> portFolio) {
        this.portFolioList = portFolio;
    }

    /* End of Getter and Setter method */
}
