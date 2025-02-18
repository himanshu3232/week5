package org.capgemini.advanced_problems;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

//converts csv to java pojo/bean
public class CsvToPojo {
    public static void main(String[] args) {
        //specify file path
        String path = "src/main/resources/student.csv";

        //read line by line
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){

            //specify csv to bean builder
            CsvToBean<Student> reader = new CsvToBeanBuilder<Student>(bufferedReader)
                    .withType(Student.class)
                    .withSeparator(',')
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            //parse the students
            List<Student> students = reader.parse();

            //print results
            for(Student student : students)
                System.out.println(student);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


