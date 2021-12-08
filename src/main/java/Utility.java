import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Utility {

    public Utility() {
    }

    //double formatter
    public static final DecimalFormat df = new DecimalFormat("0.00");

    //a method for converting string to int
    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //a method for converting string to int
    public static Double parseFloatOrNull(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1.00;
        }
    }

    //a method for preparing exchange rate from any price to euro
    public static double currencyExchange(Double fromCurrencyPrice, String currencyName){
        //csv file name for forex exchange
        String fileName = "./src/main/resources/Forex.csv";

        //Map of conversion list
        HashMap<String, Double> conversionList = new HashMap<>();
        //read forex exchange file
        ArrayList<String[]> forexExchangeList = CsvIO.LoadCsv(fileName);
        //iterate through the files to extract exchange rate to euro

        for (int i = 0; i < forexExchangeList.size(); i++) {

            //check to see if the current column is valid
            if(forexExchangeList.get(i).length == 3){
                //initialize temp product info array
                String[] tempForexInfo = forexExchangeList.get(i);
                //check if the current column is not a header
                if(!tempForexInfo[0].toLowerCase().equals("Currency".toLowerCase())){

                    String fromCurrency = tempForexInfo[0];
                    String toCurrency = tempForexInfo[1];
                    Double xChangeRate = Utility.parseFloatOrNull(tempForexInfo[2]);

                    if(fromCurrency.toLowerCase().equals("eur")){
                        conversionList.put(toCurrency.toLowerCase(), 1/xChangeRate);

                    }else{
                        conversionList.put(fromCurrency.toLowerCase(), xChangeRate);
                    }
                }
            }
        }
        //return converted currency
        return conversionList.get(currencyName.toLowerCase()) * fromCurrencyPrice;

    }


}
