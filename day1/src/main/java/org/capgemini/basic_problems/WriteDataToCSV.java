package org.capgemini.basic_problems;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.List;

//writes data to csv
public class WriteDataToCSV {
    public static void main(String[] args) {

        //open csv writer and specify path
        try(CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/employee.csv"))){

            //mock raw data rows
            String[] header = {"ID", "Name", "Department", "Salary"};
            String[] row1 = {"1", "Ayush", "Cohort 1", "1000"};
            String[] row2 = {"2", "Gaurav", "Cohort 2", "500"};
            String[] row3 = {"3", "Himanshu", "Cohort 4", "100"};
            String[] row4 = {"4", "Shriyansh", "Cohort 3", "2000"};

            //store in a list
            List<String[]> rows = List.of(header, row1, row2, row3, row4);

            //write all
            writer.writeAll(rows);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
