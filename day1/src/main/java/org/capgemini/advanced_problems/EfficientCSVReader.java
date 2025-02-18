package org.capgemini.advanced_problems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EfficientCSVReader {

    private static final int CHUNK_SIZE = 100;  // Number of records to process at a time

    // Method to read the CSV file in chunks
    public static void readCSVInChunks(String inputFile) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {
            List<String[]> chunk = new ArrayList<>();
            int recordCount = 0;
            int chunkCount = 0;
            String[] strings;
            // Reading the CSV file in chunks of 100 lines
            while ((strings = reader.readNext()) != null) {
                chunkCount++;
                recordCount++;

                chunk.add(strings);

                // Process the chunk (print the record count for demonstration)
                System.out.println("Processing chunk #" + chunkCount + " with " + CHUNK_SIZE + " records.");

                // Process the records in the current chunk (for example, print each record)
                for (String[] record : chunk) {
                    // Process each record (for now, just print it)
                    System.out.println(String.join(", ", record));
                }

                // Simulate processing only 100 records at a time
                if (recordCount == CHUNK_SIZE) {
                    System.out.println("Processed 100 records.");
                    recordCount = 0;  // Reset record count for next chunk
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String inputFile = "src/main/resources/employee.csv";  // Path to the large CSV file
            readCSVInChunks(inputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
