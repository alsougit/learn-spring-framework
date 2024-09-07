package com.turki.spring.learnspringframework.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turki.spring.learnspringframework.Model.Course;

@RestController
public class CourseController {
    // http://localhost:8080/myApi/courses

    @RequestMapping("/myApi/courses")
    public List<Course> retreiveAllCourses() {
        return Arrays.asList(
                new Course(1, "Learn AWS", "in28Minutes"),
                new Course(2, "Learn Swift", "UdemyCourse"),
                new Course(3, "Learn SpringBoot", "in28Minutes"));
    }


}
