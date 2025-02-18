package org.capgemini.advanced_problems;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

// Detects duplicate entries based on the "ID" column and prints them
public class DetectDuplicatesInCSV {
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

            // Identify the ID column index
            int idIndex = -1;
            for (int i = 0; i < header.length; i++) {
                if (header[i].trim().equalsIgnoreCase("id")) {
                    idIndex = i;
                    break;
                }
            }

            // Validate if ID column exists
            if (idIndex == -1) {
                System.out.println("Error: 'ID' column not found in the CSV header.");
                return;
            }

            // Track unique and duplicate IDs
            Set<String> uniqueIds = new HashSet<>();
            List<String[]> duplicateRecords = new ArrayList<>();

            // Read each row and check for duplicates
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (row.length > idIndex) {
                    String id = row[idIndex].trim();
                    if (!uniqueIds.add(id)) { // If ID is already present, it's a duplicate
                        duplicateRecords.add(row);
                    }
                }
            }

            // Print duplicate records
            if (!duplicateRecords.isEmpty()) {
                System.out.println("🔴 Duplicate Records Found:");
                for (String[] duplicate : duplicateRecords) {
                    System.out.println(String.join(" | ", duplicate));
                }
            } else {
                System.out.println("✅ No duplicate records found.");
            }

        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
