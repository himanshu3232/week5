package org.capgemini.practice_problems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class IPLCensorAnalyzer {

    private static final String JSON_INPUT_PATH = "src/main/resources/ipl_data.json";
    private static final String JSON_OUTPUT_PATH = "src/main/resources/censored_ipl_data.json";
    private static final String CSV_INPUT_PATH = "src/main/resources/ipl_data.csv";
    private static final String CSV_OUTPUT_PATH = "src/main/resources/censored_ipl_data.csv";

    public static void main(String[] args) {
        try {
            // Process JSON
            processJsonFile();

            // Process CSV
            processCsvFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Reads JSON, applies censorship, and writes back to a new JSON file.
     */
    private static void processJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode matches = (ArrayNode) objectMapper.readTree(new File(JSON_INPUT_PATH));

        for (JsonNode match : matches) {
            ((ObjectNode) match).put("team1", censorTeamName(match.get("team1").asText()));
            ((ObjectNode) match).put("team2", censorTeamName(match.get("team2").asText()));
            ((ObjectNode) match).put("player_of_match", "REDACTED");
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_OUTPUT_PATH), matches);
    }

    /**
     * Reads CSV, applies censorship, and writes back to a new CSV file.
     */
    private static void processCsvFile() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        List<JsonNode> matches = csvMapper.readerFor(JsonNode.class)
                .with(schema)
                .<JsonNode>readValues(new File(CSV_INPUT_PATH))
                .readAll();

        for (JsonNode match : matches) {
            ((ObjectNode) match).put("team1", censorTeamName(match.get("team1").asText()));
            ((ObjectNode) match).put("team2", censorTeamName(match.get("team2").asText()));
            ((ObjectNode) match).put("player_of_match", "REDACTED");
        }

        csvMapper.writer(schema.withHeader()).writeValue(new File(CSV_OUTPUT_PATH), matches);
    }

    /**
     * Censors team names by replacing the second word with "***".
     */
    private static String censorTeamName(String teamName) {
        String[] words = teamName.split(" ");
        if (words.length > 1) {
            return words[0] + " ***";
        }
        return teamName;
    }
}

