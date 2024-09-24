package com.turki.spring.learnspringframework.Model.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.turki.spring.learnspringframework.Model.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class CourseJpaRepository {
    // This is how to use JPA not Spring JPA ( JPA depends on EntityManager)
    @PersistenceContext //this is like @Autowired but specific for EntityManager
    private EntityManager manager;

    public void insert(Course course){
        manager.merge(course);
    }

    public void deleteById(long id){
        Course course = manager.find(Course.class, id);
        manager.remove(course);
    }

    public Course findById(long id){
        return manager.find(Course.class, id);
    }

    public void updateCourse(long id, Course updatedCourse){
        Course course = manager.find(Course.class, id);
        course.setName(updatedCourse.getName());
        course.setAuthor(updatedCourse.getAuthor());
        manager.merge(course);
    }

}
