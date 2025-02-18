package org.capgemini.advanced_problems;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MergeCSV {
    public static void main(String[] args) {
        String path1 = "src/main/resources/student1.csv";
        String path2 = "src/main/resources/student2.csv";

        String[] header1 = getHeader(path1);
        String[] header2 = getHeader(path2);
        Set<String> header = new LinkedHashSet<>();
        if(header1 != null) {
            header.addAll(Arrays.stream(header1).toList());
        }
        if(header2 != null) {
            header.addAll(Arrays.stream(header2).toList());
        }

    }
    static String[] getHeader(String path){
        try(CSVReader reader = new CSVReader(new FileReader(path))){
            return reader.readNext();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
