package com.turki.spring.learnspringframework.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.Model.Course;

@Component
public class CourseDetailsService {

    public enum StatusEnum{
        SUCCESS,FAILURE;
    }
    public static List<Course> courses = new ArrayList<>();
    static {
        Course course1 = new Course(1, "Spring Core", "Descripting the Spring Core in basics");
        courses.add(course1);

        Course course2 = new Course(2, "Spring MVC", "Basics of Spring Model-View-Controller Design Pattern");
        courses.add(course2);

        Course course3 = new Course(3, "Soap webservices",
                "Structure way of creating soap web services using spring boot");
        courses.add(course3);

        Course course4 = new Course(4, "Spring security", "how to apply the security in the spring boot");
        courses.add(course4);
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;

    }

    public List<Course> findAll() {
        return courses;
    }

    public StatusEnum deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                return StatusEnum.SUCCESS;
            }
        }
        return StatusEnum.FAILURE;
    }

}
