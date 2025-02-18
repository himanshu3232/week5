package org.capgemini.intermediate_problem;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Scanner;

//searches employee in a csv and prints the department and salary
public class SearchInCSV {
    public static void main(String[] args) {
        //specify file path
        String path = "src/main/resources/employee.csv";

        //Fetch employee name
        System.out.print("Enter employee name: ");
        String employeeName = new Scanner(System.in).nextLine().toLowerCase();

        //open csv reader
        try(CSVReader reader = new CSVReader(new FileReader(path))){

            //fetch header and get appropriate indexes
            String[] header = reader.readNext();
            int departmentIndex = -1;
            int salaryIndex = -1;
            int nameIndex = -1;
            int currentIndex = 0;
            for(String column : header){
                if(column.trim().toLowerCase().equals("name"))
                    nameIndex = currentIndex;
                if(column.trim().toLowerCase().equals("salary"))
                    departmentIndex = currentIndex;
                if(column.trim().toLowerCase().equals("department"))
                    salaryIndex = currentIndex;
                currentIndex++;
            }

            //terminate program if any of the required index is not found
            if(nameIndex == -1 || salaryIndex == -1 || departmentIndex == -1){
                System.out.println("Error while reading the header");
                return;
            }

            //search employee with name
            String[] row;
            while((row = reader.readNext()) != null){
                //
                if(row[nameIndex].trim().toLowerCase().equals(employeeName)){
                    System.out.print("Name: " + employeeName + ", Department: " + row[departmentIndex]
                    + ", Salary: " + row[salaryIndex]);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
