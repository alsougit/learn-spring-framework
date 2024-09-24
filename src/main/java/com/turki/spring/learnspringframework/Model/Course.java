package com.turki.spring.learnspringframework.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity //if table name and class name are different use name attribute: (name = "give the name of the table")
public class Course {

    @Id //to define the primary key
    private long id;
    //the below not mandatory as the Variable name same as Column name in this table, otherwise it can be specified 
    //@Column(name = "name-of-table-cloumn")
    private String name;
    private String author;

    public Course(){
        
    }

    public Course(long id, String name, String Author){
        this.id = id;
        this.name = name;
        this.author = Author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        author = author;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", Author=" + author + "]";
    }

    
}
