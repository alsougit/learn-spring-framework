package com.turki.spring.learnspringframework.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.turki.spring.learnspringframework.Model.jdbc.CourseJdbcRepository;
import com.turki.spring.learnspringframework.Model.jpa.CourseRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    //@Autowired
    //private CourseJdbcRepository courseRepo;
    @Autowired
    private CourseRepository courseJpaRepo;

    @Override
    public void run(String... args) throws Exception {

        Course jdbcCourse = new Course(1, "Learn JDBC", "TechnoSchool");
        Course devopsCourse = new Course(2, "Learn DevOps", "TechnoSchool");
        Course javaCourse = new Course(3, "Learn JAVA", "AWSchool");
        /**
         * //Use of Spring JDBC:
         * courseRepo.insertCourse(jdbcCourse);
         * courseRepo.insertCourse(devopsCourse);
         * courseRepo.insertCourse(javaCourse);
         * 
         * courseRepo.deleteById(devopsCourse.getId());
         * courseRepo.updateCourse(jdbcCourse.getId(), javaCourse);
         * 
         * System.out.println("Course: " + courseRepo.findById(1));
         * System.out.println("Course: " + courseRepo.findById(3));
         */

         
        //Use of Spring jpa:
        courseJpaRepo.save(jdbcCourse);
        courseJpaRepo.save(devopsCourse);
        courseJpaRepo.save(javaCourse);

        System.out.println(courseJpaRepo.findAll());
        System.out.println(courseJpaRepo.findByAuthor("TechnoSchool"));
        System.out.println(courseJpaRepo.findByName("Learn JAVA"));
        

    }

}
