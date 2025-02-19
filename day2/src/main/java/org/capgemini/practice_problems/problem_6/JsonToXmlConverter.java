package org.capgemini.practice_problems.problem_6;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JsonToXmlConverter {
    public static void main(String[] args) throws Exception {
        // Sample JSON input
        String json = "{ \"student\": { \"name\": \"John Doe\", \"age\": 25, \"subjects\": [\"Math\", \"Science\"] } }";

        // Convert JSON to XML
        String xml = convertJsonToXml(json);

        // Print the XML output
        System.out.println(xml);
    }

    public static String convertJsonToXml(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(jsonNode);
    }
}
