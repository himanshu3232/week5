package org.capgemini.practice_problems.problem_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.capgemini.hands_on_problems.problem_1.Student;
import java.util.List;

public class ConvertObjectListToJson {
    public static void main(String[] args) {

        //mock object list
        List<Student> studentList = List.of(new Student("Student1", 21, List.of("a", "b", "c")),
                new Student("Student2", 25, List.of("a", "b", "c")),
                new Student("Student3", 28, List.of("a", "b", "c")),
                new Student("Student4", 29, List.of("a", "b", "c")));

        //convert to json
        JsonNode node = new ObjectMapper().valueToTree(studentList);

        //print json
        System.out.println(node.toPrettyString());
    }
}
