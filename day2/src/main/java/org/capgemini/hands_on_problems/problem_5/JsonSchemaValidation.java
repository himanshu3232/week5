package org.capgemini.hands_on_problems.problem_5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JsonSchemaValidation {
    public static boolean validateJsonSchema(String json, String schema) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode schemaNode = mapper.readTree(schema);

            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

            ProcessingReport report = jsonSchema.validate(jsonNode);

            return report.isSuccess();
        } catch (Exception e) {
            System.err.println("Schema Validation Failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String schema = "{ \"type\": \"object\", \"properties\": { \"name\": { \"type\": \"string\" }, \"age\": { \"type\": \"integer\", \"minimum\": 18 } }, \"required\": [\"name\", \"age\"] }";
        String validJson = "{\"name\": \"Himanshu\", \"age\": 24}";
        String invalidJson = "{\"name\": \"Himanshu\", \"age\": 16}";

        System.out.println(validateJsonSchema(validJson, schema));
        System.out.println(validateJsonSchema(invalidJson, schema));
    }
}
