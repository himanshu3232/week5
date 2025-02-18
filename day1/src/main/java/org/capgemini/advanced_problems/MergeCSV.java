package org.capgemini.advanced_problems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) {
        String path1 = "src/main/resources/student1.csv";
        String path2 = "src/main/resources/student2.csv";

        List<String[]> data1 = new ArrayList<>(getData(path1));
        List<String[]> data2 = new ArrayList<>(getData(path2));

        Set<Integer> commonIndexes = new HashSet<>();
        Set<String> mergedHeads = new LinkedHashSet<>(Arrays.stream(data1.get(0)).toList());
        int index = 0;
        for(String s: data2.get(0)){
            if(mergedHeads.contains(s)){
                commonIndexes.add(index++);
                continue;
            }
            mergedHeads.add(s);
        }

        List<String[]> newData = new ArrayList<>();
        int len = Math.min(data1.size(), data2.size());


        for (int i = 0; i < len; i++) {
            String[] newRow = new String[data1.get(0).length + data2.get(0).length - commonIndexes.size()];
            index = 0;
            for(String s : data1.get(i)){
                newRow[index++] = s;
            }
            for (int j = 0; j < data2.get(0).length; j++) {
                if(!commonIndexes.contains(j)){
                    newRow[index++] = data2.get(i)[j];
                }
            }

            newData.add(newRow);
        }

        for(String[] row : newData){
            System.out.println(Arrays.toString(row));
        }


        writeNewData(newData, "src/main/resources/student3.csv");
    }

    static List<String[]> getData(String path){
        List<String[]> data = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(path))){
            String[] row;
            while((row = reader.readNext()) != null){
                data.add(row);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return data;
    }

    static void writeNewData(List<String[]> newData, String path){
        try(CSVWriter writer = new CSVWriter(new FileWriter(path))){
            writer.writeAll(newData);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
