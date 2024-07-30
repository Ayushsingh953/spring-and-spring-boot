package com.ayush953.first_web_app.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1,"Ayush","Learn Apache Kafka", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(2,"Ayush","Learn Docker", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(3,"Ayush","Learn Spring Boot", LocalDate.now().plusYears(1),false));
    }

    public List<Todo> getTodosByName(String name) {
        return todos;
    }
}
