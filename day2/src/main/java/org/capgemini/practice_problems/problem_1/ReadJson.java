package org.capgemini.practice_problems.problem_1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    public static void main(String[] args) {
        //open a reader
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/JsonExample.json"))){
            ObjectMapper objectMapper = new ObjectMapper();


            //read file and parse to json
            JsonNode json = objectMapper.readTree(reader);

            //print the output
            System.out.println(json.toPrettyString());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
