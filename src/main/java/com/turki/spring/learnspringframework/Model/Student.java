package com.turki.spring.learnspringframework.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String studentGrade;
    public enum Grade{
        A,B,C,D,F
    }

    public Student(int id, String name, int age, String studentGrade){
        this.id = id;
        this.name = name;
        this.age = age;
        this.studentGrade = studentGrade;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    };

    public void setStudentGrade(String grade){
        studentGrade = grade;
    }
    public String getStudentGrade(){
        return studentGrade;
    }

}
