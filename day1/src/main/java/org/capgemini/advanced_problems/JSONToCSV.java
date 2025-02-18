package org.capgemini.advanced_problems;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONToCSV {

    public static void jsonToCsv(String jsonString, String csvFile) throws IOException {
        // Simulate the JSON array of objects
        List<Map<String, String>> jsonList = new ArrayList<>();
        jsonList.add(Map.of("id", "1", "name", "John", "age", "22"));
        jsonList.add(Map.of("id", "2", "name", "Jane", "age", "24"));
        jsonList.add(Map.of("id", "3", "name", "Jack", "age", "20"));

        // Prepare CSVWriter to write to file
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Write the headers (keys from the first object in the JSON list)
            String[] headers = jsonList.get(0).keySet().toArray(new String[0]);
            writer.writeNext(headers);

            // Write data (values of each object)
            for (Map<String, String> jsonObject : jsonList) {
                String[] data = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    data[i] = jsonObject.get(headers[i]);
                }
                writer.writeNext(data);
            }
        }

        System.out.println("CSV file created successfully.");
    }

    public static void main(String[] args) throws IOException {
        // Simulating the JSON String, as we are not using a JSON library here
        String jsonString = "[{\"id\": 1, \"name\": \"John\", \"age\": 22}, {\"id\": 2, \"name\": \"Jane\", \"age\": 24}, {\"id\": 3, \"name\": \"Jack\", \"age\": 20}]";
        jsonToCsv(jsonString, "students.csv");
    }
}

