package org.capgemini.practice_problems.problem_5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

//merges two json files
public class MergeJson {
    public static void main(String[] args) {
        //specify paths
        String path1 = "src/main/resources/json1.json";
        String path2 = "src/main/resources/json2.json";
        String outputPath = "src/main/resources/json3.json";

        //get json nodes
        JsonNode node1 = getJson(path1);
        JsonNode node2 = getJson(path2);

        //Get a mutable node through mapper
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        //merge here
        objectNode.setAll((ObjectNode) node1);
        objectNode.setAll((ObjectNode) node2);

        //define the output
        if(writeJson(objectNode, outputPath)){
            System.out.println("Merged successfully");
        }else {
            System.out.println("Error merging");
        }
    }

    //get json from here
    private static JsonNode getJson(String path){
        JsonNode node = null;
        try(Reader reader = new FileReader(path)){
            node = new ObjectMapper().readTree(reader);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        return node;
    }

    //write json to file from here
    private static boolean writeJson(JsonNode node, String path){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(writer,node);
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}

