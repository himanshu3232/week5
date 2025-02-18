package org.capgemini.advanced_problems;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.regex.Pattern;

// Validates CSV data and prints invalid rows
public class ValidateCSVData {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/resources/employee.csv";

        // Define regex patterns
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$"); // Exactly 10 digits

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Read the header
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("Error: Empty CSV file.");
                return;
            }

            // Identify column indexes
            int emailIndex = -1;
            int phoneIndex = -1;

            for (int i = 0; i < header.length; i++) {
                String column = header[i].trim().toLowerCase();
                if (column.equals("email"))
                    emailIndex = i;
                else if (column.equals("phone"))
                    phoneIndex = i;
            }

            // Validate if required columns exist
            if (emailIndex == -1 || phoneIndex == -1) {
                System.out.println("Error: Missing required columns in the CSV header.");
                return;
            }

            // Validate each row
            String[] row;
            boolean hasInvalidRows = false;
            while ((row = reader.readNext()) != null) {
                String email = row[emailIndex].trim();
                String phone = row[phoneIndex].trim();

                boolean isValidEmail = emailPattern.matcher(email).matches();
                boolean isValidPhone = phonePattern.matcher(phone).matches();

                if (!isValidEmail || !isValidPhone) {
                    hasInvalidRows = true;
                    System.out.print("Invalid row found: ");
                    for (String cell : row) {
                        System.out.print(cell + " | ");
                    }
                    System.out.println();
                    if (!isValidEmail) {
                        System.out.println("Invalid Email: " + email);
                    }
                    if (!isValidPhone) {
                        System.out.println("Invalid Phone: " + phone);
                    }
                }
            }

            if (!hasInvalidRows) {
                System.out.println("All data is valid.");
            }

        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
