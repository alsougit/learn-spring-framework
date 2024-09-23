package com.turki.spring.learnspringframework.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turki.spring.learnspringframework.Model.Student;
import com.turki.spring.learnspringframework.Model.StudentRepository;

@Service

public class StudentService {
    
    @Autowired
    private StudentRepository studentRepo;

    public static List<Student> students = new ArrayList<>();
    static{
        Student student1 = new Student(1, "Turki", 20, "A");
        students.add(student1);
        Student student2 = new Student(2, "Ahmed", 30, "A");
        students.add(student2);
        Student student3 = new Student(3, "Ali", 25, "C");
        students.add(student3);
        Student student4 = new Student(4, "Aziz", 29, "B");
        students.add(student4);
        
    }


    public Student addStudent(Student student){
        
        return studentRepo.save(student);
    }

    public Student getStudentById(int id){
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student updateStudent(int id, Student updatedStudent){
        Student student = studentRepo.findById(id).orElse(null);
        if(student != null){
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setStudentGrade(updatedStudent.getStudentGrade());

            return studentRepo.save(student);
        }
        return null;
    }

    public void deleteStudent(int id){
        studentRepo.deleteById(id);
    }
    

}
