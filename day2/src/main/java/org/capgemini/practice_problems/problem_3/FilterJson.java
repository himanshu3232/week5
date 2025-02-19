package org.capgemini.practice_problems.problem_3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.capgemini.hands_on_problems.problem_1.Student;

import java.io.IOException;
import java.util.List;

public class FilterJson {
    public static void main(String[] args) throws IOException {
        //mock object
        List<Student> studentList = List.of(new Student("Student1", 21, List.of("a", "b", "c")),
                new Student("Student2", 25, List.of("a", "b", "c")),
                new Student("Student3", 28, List.of("a", "b", "c")),
                new Student("Student4", 29, List.of("a", "b", "c")));

        ObjectMapper mapper = new ObjectMapper();

        //convert to json node
        JsonNode jsonNode = mapper.valueToTree(studentList);

        //check if a valid array
        if(jsonNode.isArray()){
            //iterate over it
            for(JsonNode node : jsonNode){
                //check if age is above 25
                if(node.get("age").asInt() > 25)
                    System.out.println(node.toPrettyString());
            }
        }
    }
}
