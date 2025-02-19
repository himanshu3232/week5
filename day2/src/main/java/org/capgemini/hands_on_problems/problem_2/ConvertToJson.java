package org.capgemini.hands_on_problems.problem_2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

public class ConvertToJson {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car("Hyundai", "BR801013", LocalDateTime.now().toString());
        String mappedObject = mapper.writeValueAsString(car);

        System.out.println(mappedObject);
    }
}
