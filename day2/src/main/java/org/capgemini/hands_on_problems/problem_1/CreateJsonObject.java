package org.capgemini.hands_on_problems.problem_1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CreateJsonObject {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = new Student("Shriyansh", 22, List.of("Hindi", "English", "Math"));
        String json = mapper.writeValueAsString(student);
        System.out.println(json);
    }
}
