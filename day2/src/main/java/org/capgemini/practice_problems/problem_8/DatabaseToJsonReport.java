package org.capgemini.practice_problems.problem_8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DatabaseToJsonReport {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        try {
            String jsonOutput = fetchAndConvertToJson();
            System.out.println(jsonOutput);

            // Write JSON to file (Optional)
            writeJsonToFile(jsonOutput, "src/main/resources/JsonExample.json");

        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String fetchAndConvertToJson() throws SQLException, JsonProcessingException {
        String query = "SELECT id, name, age, department FROM employees"; // Modify table & columns

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ObjectMapper mapper = new ObjectMapper();
            ArrayNode jsonArray = mapper.createArrayNode();

            while (rs.next()) {
                ObjectNode jsonObject = mapper.createObjectNode();
                jsonObject.put("id", rs.getInt("id"));
                jsonObject.put("name", rs.getString("name"));
                jsonObject.put("age", rs.getInt("age"));
                jsonObject.put("department", rs.getString("department"));

                jsonArray.add(jsonObject);
            }

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);
        }
    }

    public static void writeJsonToFile(String jsonData, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), jsonData);
        System.out.println("JSON report saved to: " + filePath);
    }
}

