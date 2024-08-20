package com.ayush953.first_web_app.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int id = 0;

    static {
        todos.add(new Todo(++id,"Ayush","Learn Apache Kafka", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++id,"Ayush","Learn Docker", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++id,"Ayush","Learn Spring Boot", LocalDate.now().plusYears(1),false));
    }

    public List<Todo> getTodosByName(String name) {
        return todos;
    }

    public void addTodo(String username,String description,LocalDate date,boolean completed){
        todos.add(new Todo(++id,username,description,date,completed));
    }

    public void deleteTodoById(int id){
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo findById(int id){
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }
}
