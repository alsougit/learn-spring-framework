package com.turki.spring.learnspringframework.Model;

public class Course {

    private long id;
    private String name;
    private String Author;

    public Course(){
        
    }

    public Course(long id, String name, String Author){
        this.id = id;
        this.name = name;
        this.Author = Author;
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
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", Author=" + Author + "]";
    }

    
}
