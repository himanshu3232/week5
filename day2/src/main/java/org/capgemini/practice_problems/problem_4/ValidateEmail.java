package org.capgemini.practice_problems.problem_4;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class ValidateEmail {
    public static void main(String[] args) throws Exception {
        String jsonSchema = """
                {
                  "$schema": "http://json-schema.org/draft-07/schema#",
                  "type": "object",
                  "properties": {
                    "email": {
                      "type": "string",
                      "format": "email"
                    }
                  },
                  "required": ["email"]
                }""";

        String validJson = "{ \"email\": \"user@example.com\" }";
        String invalidJson = "{ \"email\": \"invalid-email\" }";

        validateJson(jsonSchema, validJson);
        validateJson(jsonSchema, invalidJson);
    }

    public static void validateJson(String schemaStr, String jsonStr) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaStr);
        JsonNode jsonNode = objectMapper.readTree(jsonStr);

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(schemaNode);

        ProcessingReport report = schema.validate(jsonNode);

        if (report.isSuccess()) {
            System.out.println(jsonNode.toPrettyString());
        } else {
            System.out.println("Invalid JSON:");
        }
    }
}
