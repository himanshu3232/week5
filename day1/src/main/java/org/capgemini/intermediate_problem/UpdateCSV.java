package org.capgemini.intermediate_problem;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Updates salaries of IT employees by 10% and saves to a new CSV file
public class UpdateCSV {
    public static void main(String[] args) {
        // Define file paths
        String inputPath = "src/main/resources/employee.csv";
        String outputPath = "src/main/resources/updated_employee.csv";

        // Read and process CSV data
        try (CSVReader reader = new CSVReader(new FileReader(inputPath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {

            // Read the header
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("Error: Empty CSV file.");
                return;
            }

            // Identify column indexes
            int departmentIndex = -1;
            int salaryIndex = -1;

            for (int i = 0; i < header.length; i++) {
                if (header[i].trim().equalsIgnoreCase("department"))
                    departmentIndex = i;
                else if (header[i].trim().equalsIgnoreCase("salary"))
                    salaryIndex = i;
            }

            // Check if required columns exist
            if (departmentIndex == -1 || salaryIndex == -1) {
                System.out.println("Error: Missing required columns in the CSV header.");
                return;
            }

            // Store updated data
            List<String[]> updatedData = new ArrayList<>();
            updatedData.add(header); // Add header to new file

            // Process each row
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (row.length > salaryIndex && row.length > departmentIndex) {
                    if (row[departmentIndex].trim().equalsIgnoreCase("IT")) {
                        try {
                            double currentSalary = Double.parseDouble(row[salaryIndex].trim());
                            row[salaryIndex] = String.format("%.2f", currentSalary * 1.10); // Increase by 10%
                        } catch (NumberFormatException e) {
                            System.out.println("Warning: Invalid salary format for " + row[0]);
                        }
                    }
                }
                updatedData.add(row);
            }

            // Write updated data to new CSV file
            writer.writeAll(updatedData);
            System.out.println("Updated CSV file saved as: " + outputPath);

        } catch (IOException | CsvValidationException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
