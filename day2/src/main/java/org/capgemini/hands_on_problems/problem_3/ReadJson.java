package org.capgemini.hands_on_problems.problem_3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        String path = "src/main/resources/JsonExample.json";

        try(FileReader reader = new FileReader(path)){
            JsonNode jsonNode = mapper.readTree(reader);
            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();

            System.out.println("Name : " + name);
            System.out.println("Email : " + email);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
