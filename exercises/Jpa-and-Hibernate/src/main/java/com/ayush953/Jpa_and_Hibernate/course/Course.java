package com.ayush953.Jpa_and_Hibernate.course;

public class Course {
    private long id;
    private String name;
    private String author;

    public Course() {

    }
    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
}
