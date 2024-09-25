package com.turki.spring.learnspringframework.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course,Integer>{
    Course findByName(String name);
    
}
