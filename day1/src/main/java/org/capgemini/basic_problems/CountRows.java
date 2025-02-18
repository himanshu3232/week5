package org.capgemini.basic_problems;

import com.opencsv.CSVReader;

import java.io.FileReader;

//prints the number of rows in csv file
public class CountRows {
    public static void main(String[] args) {

        //print the number of rows in csv
        System.out.println(countRowsInCSV("src/main/resources/employee.csv"));
    }

    private static int countRowsInCSV(String path){
        int count = 0;
        String[] line;

        //open reader and count lines
        try(CSVReader reader = new CSVReader(new FileReader(path))){
            while((line = reader.readNext()) != null)
                count++;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //return count excluding the header
        return count-1;
    }
}
