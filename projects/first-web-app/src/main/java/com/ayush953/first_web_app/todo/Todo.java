package com.ayush953.first_web_app.todo;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Todo {
    private int id;
    private String username;

    @Size(min=10,message = "Enter atleast 10 characters")
    private String description;
    private LocalDate targetDate;
    private boolean completed;

    public Todo(int id, String username, String description, LocalDate targetDate, boolean completed) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getTargetDate() {
        return targetDate;
    }
    public boolean isCompleted() {
        return completed;
    }

    public String toString() {
        return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate=" + targetDate + ", completed=" + completed + "]";
    }
}
