package com.ayush953.first_web_app.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
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

    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String newTodo(){
        return "addTodo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(){
        return "redirect:list-todos";
    }
}
