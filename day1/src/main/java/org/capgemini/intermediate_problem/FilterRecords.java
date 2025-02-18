package org.capgemini.intermediate_problem;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//filters record of students with marks less than 80
public class FilterRecords {
    public static void main(String[] args) {
        //specify path of csv file
        String path = "src/main/resources/student.csv";
        List<String[]> csv = new ArrayList<>();
        String[] header = null;
        //open csv reader
        try(CSVReader reader = new CSVReader(new FileReader(path))){
            String[] row;

            //skip the first row and store it
            header = reader.readNext();

            while((row = reader.readNext()) != null){
                csv.add(row);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //specify file as empty in case
        if(csv.isEmpty()){
            System.out.println("File is empty");
            return;
        }

        //iterate through the rows and filter the students with marks less than 81
        List<String[]> filteredStudents = csv.stream()
                .filter(row -> Integer.parseInt(row[row.length-1].trim()) > 80)
                .toList();

        //print header
        if(header != null)
            System.out.println(Arrays.toString(header));

        //print filtered students
        for(String[] row : filteredStudents){
            System.out.println(Arrays.toString(row));
        }
    }
}
