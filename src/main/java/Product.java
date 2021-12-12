import java.util.ArrayList;
import java.util.HashMap;

public class Product extends Item{



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

}
