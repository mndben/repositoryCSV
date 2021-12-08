import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvIO {

    public CsvIO() {

    }

    //a method to write a string to csv
    public void writeToCsv(String[] details,String filePath){

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

    //a method for writing the two csv files
    public void writeResultToCsv(ArrayList<PortFolio> portFolio, HashMap<String, Double> clientCapitalMap){

        //initialize file path
        String pathPortfolio =  "./src/main/resources/result/Reporting-portfolio.csv";
        String pathClient = "./src/main/resources/result/Reporting-client.csv";



        //write portfolio capital to csv
        for (int i = 0; i < portFolio.size(); i++) {

            //portfolio name and capital
            String portfolioName = portFolio.get(i).getName();
            Double portfolioCapital = portFolio.get(i).getPrice();

            //write to csv
            if(i == 0){
                writeToCsv(new String[]{"PTF", "price"}, pathPortfolio);
            }
            String[] portFolioDetails = {portfolioName,Utility.df.format(portfolioCapital)};
            writeToCsv(portFolioDetails, pathPortfolio);

        }

        //write client capital to csv
        int i = 0;
        for (String key : clientCapitalMap.keySet()) {

            //portfolio name and capital
            String clientName = key;
            Double clientCapital = clientCapitalMap.get(key);

            //write to csv
            if(i == 0){
                writeToCsv(new String[]{"Client", "Capital"}, pathClient);
            }
            String[] clientDetails = {clientName,Utility.df.format(clientCapital)};
            writeToCsv(clientDetails, pathClient);

            i+=1;
        }
    }

    //a method for loading csv files
    public static ArrayList<String[]> LoadCsv(String fileName){

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
        //return result
        return data;

    }

}
