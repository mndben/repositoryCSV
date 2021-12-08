import java.util.ArrayList;
import java.util.HashMap;

public class PortFolio {

    //constructor variables
    private String name;
    private ArrayList<String> products;
    private Double price;

    public PortFolio(String name, ArrayList<String> products, Double price) {
        this.name = name;
        this.products = products;
        this.price = price;
    }

    //empty constructor
    public PortFolio() {
    }

    //getter and setter method
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    //a method for constructing portfolio object list contains portfolio name and products under it
    public ArrayList<PortFolio> portfolioDetails(ArrayList<String> portfolioList, HashMap<String, ProductDetail> productDetails, HashMap<String, Integer> productCount){

        /* Array List to store portfolio detail
         * */
        ArrayList<PortFolio> porFolioObjectArrayList = new ArrayList<>();

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
            for (String key : productDetails.keySet()) {

                ProductDetail tempProductDetail = productDetails.get(key);
                Double tempProductPrice = tempProductDetail.getPrice();
                //check to see if product belongs to portfolio
                if(tempProductDetail.getPortfolio().toLowerCase().equals(tempPortfolioName)){

                    tempProductList.add(key);
                    tempPortFolioPrice+=tempProductPrice*productCount.get(key);
                }
            }/* end of product details loop*/

            /*store portfolio info for the current product
             * */
            //initialise portfolio object
            PortFolio tempPortFolioObject = new PortFolio(tempPortfolioName,tempProductList,tempPortFolioPrice);

            //add object to arraylist
            porFolioObjectArrayList.add(tempPortFolioObject);
        }

        return porFolioObjectArrayList;

    }
}
