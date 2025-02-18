package org.capgemini.intermediate_problem;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Reads a CSV file, sorts by salary in descending order, and prints top 5 highest-paid employees
public class SortCSVBySalary {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/resources/employee.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            // Read the header
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("Error: Empty CSV file.");
                return;
            }

            // Identify column indexes
            int nameIndex = -1;
            int departmentIndex = -1;
            int salaryIndex = -1;

            for (int i = 0; i < header.length; i++) {
                String column = header[i].trim().toLowerCase();
                if (column.equals("name"))
                    nameIndex = i;
                else if (column.equals("department"))
                    departmentIndex = i;
                else if (column.equals("salary"))
                    salaryIndex = i;
            }

            // Validate required columns
            if (nameIndex == -1 || departmentIndex == -1 || salaryIndex == -1) {
                System.out.println("Error: Missing required columns in the CSV header.");
                return;
            }

            // Read and store data rows
            List<String[]> records = new ArrayList<>();
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (row.length > salaryIndex) {
                    records.add(row);
                }
            }

            // Sort records by salary in descending order
            int finalSalaryIndex = salaryIndex;
            records.sort((a, b) -> {
                try {
                    double salaryA = Double.parseDouble(a[finalSalaryIndex].trim());
                    double salaryB = Double.parseDouble(b[finalSalaryIndex].trim());
                    return Double.compare(salaryB, salaryA); // Descending order
                } catch (NumberFormatException e) {
                    return 0; // Ignore invalid salary values
                }
            });

            // Print the top 5 highest-paid employees
            System.out.println("Top 5 Highest-Paid Employees:");
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                String[] employee = records.get(i);
                System.out.println((i + 1) + ". Name: " + employee[nameIndex] +
                        ", Department: " + employee[departmentIndex] +
                        ", Salary: " + employee[salaryIndex]);
            }

        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
