package org.capgemini.practice_problems.problem_7;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvToJson {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/data.csv"; // CSV file path
        String jsonFilePath = "src/main/resources/data.json"; // JSON output path

        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<Map<String, String>> recordsList = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                Map<String, String> recordMap = new HashMap<>();
                record.forEach(recordMap::put);
                recordsList.add(recordMap);
            }

            // Convert List to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), recordsList);

            System.out.println("CSV successfully converted to JSON!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
