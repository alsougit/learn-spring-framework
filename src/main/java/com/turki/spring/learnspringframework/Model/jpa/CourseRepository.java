package com.turki.spring.learnspringframework.Model.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turki.spring.learnspringframework.Model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {

     List<Course> findByAuthor(String author);
     List<Course> findByName(String courseName);
}
