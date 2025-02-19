package org.capgemini.hands_on_problems.problem_6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.capgemini.hands_on_problems.problem_1.Student;

import java.util.ArrayList;
import java.util.List;

public class ConvertListToJson {
    public static void main(String[] args) throws JsonProcessingException {

        //mock a list of students
        List<Student> studentList = List.of(new Student("Student1", 11, List.of("a", "b", "c")),
                new Student("Student2", 15, List.of("a", "b", "c")),
                new Student("Student3", 13, List.of("a", "b", "c")),
                new Student("Student4", 12, List.of("a", "b", "c")));

        //store result in a list
        List<String> listOfJson = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        //iterate through students and write as string
        for(Student s : studentList){
            listOfJson.add(mapper.writer().writeValueAsString(s));
        }

        //iterate over results
        for(String s : listOfJson)
            System.out.println(s);
    }
}
