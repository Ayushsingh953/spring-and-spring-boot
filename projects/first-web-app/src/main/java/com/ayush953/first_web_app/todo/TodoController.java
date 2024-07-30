package com.ayush953.first_web_app.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String listTodos(ModelMap map) {
        List<Todo> todos = todoService.getTodosByName("ayush");
        map.put("todos",todos);
        return "listTodos";
    }
}
