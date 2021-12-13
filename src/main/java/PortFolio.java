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

    /* End of Getter and Setter method */
    public HashMap<String, Double> processPortfolioDetails(ArrayList<String> portfolioList, ArrayList<Product> productList, HashMap<String, Integer> productCount){

        /* Array List to store portfolio detail
         * */
        HashMap<String, Double> porFolioDetails = new HashMap<>();

        /*loop through the available portfolios and
        construct an array list of portfolios and it's products
        */
        for (int i = 0; i < portfolioList.size(); i++) {

            String tempPortfolioName = portfolioList.get(i).toLowerCase();
            ArrayList<String> tempProductList = new ArrayList<>();
            Double tempPortFolioPrice = 0.00;

            /*loop through each product in product details array list and
            check if we have a product for the current portfolio
            * */

            for (int j = 0; j < productList.size(); j++) {

                Product tempProductDetail = productList.get(j);
                Double tempProductPrice = tempProductDetail.getPrice();

                //check to see if product belongs to portfolio
                if (tempProductDetail.getPortfolio().toLowerCase().equals(tempPortfolioName)) {

                    tempProductList.add(tempProductDetail.getName());
                    tempPortFolioPrice += tempProductPrice * productCount.get(tempProductDetail.getName());
                }
            }/* end of product details loop*/

            /*store portfolio info for the current product
             * */
            //add details to arraylist
            porFolioDetails.put(tempPortfolioName,tempPortFolioPrice);
        }

        return porFolioDetails;

    }
}
