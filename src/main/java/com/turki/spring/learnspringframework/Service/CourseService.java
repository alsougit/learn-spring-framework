package com.turki.spring.learnspringframework.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.Model.Course;
import com.turki.spring.learnspringframework.Model.jpa.CourseRepository;

@Component
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepo;


    public void insertCourse(Course course){
        courseRepo.save(course);
    }

}
