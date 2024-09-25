package com.turki.spring.learnspringframework.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.Model.Course;
import com.turki.spring.learnspringframework.Model.CourseRepository;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public enum StatusEnum {
        SUCCESS, FAILURE;
    }

    public StatusEnum addCourse(Course course) {

        Course createdCourse = courseRepo.save(course);
        if (createdCourse != null)
            return StatusEnum.SUCCESS;

        return StatusEnum.FAILURE;

    }

    public Course getCourse(int id) {
        return courseRepo.findById(id).orElse(null);
    }

    public Course getCourse(String name){
        Course course = courseRepo.findByName(name);
        if(course != null){
            return course;
        }
        return null;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public StatusEnum deleteCourse(int id) {
        Course course = courseRepo.findById(id).orElse(null);
        if (course != null) {
            courseRepo.delete(course);
            return StatusEnum.SUCCESS;
        }
        return StatusEnum.FAILURE;
    }

    public StatusEnum deleteCourse(String name) {
        Course course = courseRepo.findByName(name);
        if (course != null) {
            courseRepo.delete(course);
            return StatusEnum.SUCCESS;
        }
        return StatusEnum.FAILURE;
    }

    public StatusEnum deleteAllCourses() {
        courseRepo.deleteAll();
        return StatusEnum.SUCCESS;
    }

}
