package SortSearchTimeTesting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CsvWriter {

    public static void toCsv(String csvString, String fileName) throws IOException {
        String fileLocation = "src/CsvOutput/" + fileName + ".csv";

        FileWriter fw = new FileWriter(fileLocation);
        BufferedWriter writer = new BufferedWriter(fw);

        try{
            writer.write(csvString);
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
