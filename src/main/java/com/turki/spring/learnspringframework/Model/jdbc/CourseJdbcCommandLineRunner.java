package com.turki.spring.learnspringframework.Model.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.Model.Course;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository courseRepo;

    @Override
    public void run(String... args) throws Exception {
        Course jdbcCourse = new Course(1, "Learn JDBC", "TechnoSchool");
        Course devopsCourse = new Course(2, "Learn DevOps", "TechnoSchool");
        Course javaCourse = new Course(3, "Learn JAVA", "TechnoSchool");
        courseRepo.insertCourse(jdbcCourse);
        courseRepo.insertCourse(devopsCourse);
        courseRepo.insertCourse(javaCourse);

        courseRepo.deleteById(devopsCourse.getId());
        courseRepo.updateCourse(jdbcCourse.getId(), javaCourse);

        System.out.println("Course:  " + courseRepo.findById(1));
        System.out.println("Course:  " + courseRepo.findById(3));

    }

}
