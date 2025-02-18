package org.capgemini.basic_problems;

import com.opencsv.CSVReader;

import java.io.FileReader;

//reads data from a csv file and prints it
public class ReadCSVAndPrintData {
    public static void main(String[] args) {
        //specify path
        String path = "src/main/resources/student.csv";

        //open csv reader
        try(CSVReader reader = new CSVReader(new FileReader(path))){
            String[] line;

            //read lines till its not null
            while((line = reader.readNext()) != null) {

                //print each words, separated by comma
                for(String s : line)
                    System.out.print(s + ", ");

                //next line
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
