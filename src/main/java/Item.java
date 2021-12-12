import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

abstract class Item {


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
        //return result


        return data;

    }



}