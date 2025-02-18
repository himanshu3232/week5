package org.capgemini.advanced_problems;

import com.opencsv.bean.CsvBindByName;

public class Student {
    @CsvBindByName
    private Long id;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private int age;

    @CsvBindByName
    private double marks;

    // Default constructor must be public
    public Student() {}

    // Constructor with parameters
    public Student(Long id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Public getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + ", marks=" + marks + "}";
    }
}
