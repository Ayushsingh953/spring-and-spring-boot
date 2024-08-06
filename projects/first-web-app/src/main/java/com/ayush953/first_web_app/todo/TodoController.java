package com.ayush953.first_web_app.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
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
    public String newTodo(ModelMap map){
        String username = (String)map.get("name");
        Todo todo = new Todo(0,username,"",LocalDate.now(),false);
        map.put("todo",todo);
        return "addTodo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTodo";
        }
        String username = (String)map.get("name");
        todoService.addTodo(username,todo.getDescription(), LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";
    }

    @RequestMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }
}
