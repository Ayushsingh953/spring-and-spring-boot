# Todo Web App
we will be building todo app using Spring and Spring Boot 

### Let's create a few endpoints to test different techniques to send response.
**HelloController.java** Class :
```java
package com.ayush953.first_web_app.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/hello-html")
    @ResponseBody
    public String helloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>Hello World</h1>");
        return sb.toString();
    }

    @RequestMapping("/hello-jsp")
    public String helloJsp(){
        return "sayHello";
    }
}

```
#### Explanation :
* The **@Controller** annotation marks the class as a Spring MVC controller.
* **@RequestMapping** maps HTTP requests to handler methods of MVC and REST controllers.
* **@ResponseBody** indicates that the return value of a method will be the body of the web response.
* **/hello**: Returns a plain text "Hello World".
* **/hello-html**: Returns an HTML string with "Hello World".
*  **/hello-jsp**: Returns the view name sayHello for rendering with JSP.

**Note** : we haven't used **@ResponseBody** on **/hello-jsp** endpoint because we want to return the view named **sayHello** which we have created in **src/main/resources/META-INF/resources/WEB-INF/jsp**.
We need to define the prefix and suffix in **application.properties** which will be by Spring MVC to map all the views with their corresponding jsp.

**application.properties** :
```properties
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

**sayHello.jsp** :
```html
<html>
    <head>
        <title>HTML - jsp</title>
    </head>
    <body>
        <h1>Hello JSP</h1>
    </body>
</html>
```
JSP(JavaServer Pages) a technology used for building dynamic web pages using Java. JSP allows embedding Java code in HTML pages using special JSP tags, making it easier to write and maintain dynamic web content.

## Implementing Dummy Login functionality

For now, we will create dummy login functionality to understand the flow of login
### LoginController
```java
package com.ayush953.first_web_app.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String welcome(@RequestParam String name,@RequestParam String password,ModelMap map) {

        if(authenticationService.authenticate(name,password)) {
            map.put("name", name);
            return "welcome";
        }
        map.put("Error","Invalid Credentials");
        return "login";
    }
}
```
#### Explanation
* **@Controller**: Marks the class as a Spring MVC controller.
* **@SessionAttributes("name")**: Indicates that the name attribute should be stored in the session.This allows the controller to access session data across different requests.
* **Logger logger**: For logging purposes.
* **AuthenticationService authenticationService**: Service to handle authentication logic.
* **Constructor** Injects AuthenticationService to be used within the controller.
* **login()**: Handles GET requests to /login. Returns the view name login for rendering the login page.
* **welcome()**: Handles POST requests to /login. Authenticates the user with name and password. If successful, stores name in ModelMap and returns welcome. Otherwise, sets an error message and returns login.
* **ModelMap** is a part of the Spring MVC framework used to pass data from the controller to the view.
  * It allows you to add attributes to the model which can be accessed in the view (e.g., JSP, Thymeleaf).
  * In **welcome()**, **ModelMap map** is used to store the name attribute when authentication is successful. This name attribute can then be accessed in the welcome view to display a personalized message.
  * It is also used to add an error message to the model when authentication fails, so the login view can display the error to the user.

### login and welcome jsp :

**login.jsp**
```html
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
    <div class="container">
        <h1>Login</h1>
        <pre>${Error}</pre>
        <form method="post">
            Name : <input type="text" name="name"/>
            Password : <input type="password" name="password">
            <input type="submit">
        </form>
    </div>
    </body>
</html>
```
**welcome.jsp** :
```html
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<div class="container">
<h1 style="text-align: center">Welcome ${name}!</h1>
<hr>
<h2 style="text-align: center">
    <a href="list-todos">Manage your todos</a>
</h2>
</div>
</body>
</html>
```
### AuthenticationService
```java
package com.ayush953.first_web_app.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String name,String password){
        return name.equalsIgnoreCase("xyz") && password.equalsIgnoreCase("dummy");
    }
}
```
This is not the real implementation of login functionality. We are only matching the hardcoded value to mimic the login flow.
* The AuthenticationService is injected into the LoginController via constructor injection.
* The LoginController uses this service to authenticate the user in the welcome() method.

## Implementing Todo Service
### TodoController :
```java
package com.ayush953.first_web_app.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String addTodo(ModelMap map,Todo todo){
        String username = (String)map.get("name");
        todoService.addTodo(username,todo.getDescription(), LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";
    }
}
```
#### Explanation :
* **TodoService todoService**: A service component used to handle business logic related to todos, such as retrieving and adding todo items.
* **listTodos(ModelMap map)**:
  * Handles GET requests to /list-todos.
  * Retrieve the list of todos associated with the user "ayush" from TodoService.
  * Adds the retrieved list of todos to the model under the attribute name "todos". This attribute will be accessible in the view named listTodos for rendering.
  *  Return the view name listTodos, which is used to display the list of todos.
* **newTodo(ModelMap map)**:
    * Handles GET requests to /add-todo.
    * Prepares a new Todo object for the form used to add a todo item.
    * Retrieves the session attribute name (the username) and creates a new Todo instance with default values. Adds this Todo object to the model under the attribute name "todo" which would be used in the **addTodo** view for form binding.
    * Returns the view name addTodo, which displays the form for adding a new todo item.
* **addTodo(ModelMap map, Todo todo)**:
  * Handles POST requests to /add-todo.
  * Processes the form submission to add a new todo item.
  * Retrieves the username from the session and uses it along with the Todo object submitted from the form to add a new todo item to the TodoService.
  * Redirect to /list-todos to display the updated list of todos after the new item has been added.

### Todo Service :
For now, we are not storing todos in the database.
```java
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
}
```
#### Explanation :
* **private static List<Todo> todos**: A static list that stores all todo items. This list is shared across all instances of TodoService, simulating a data repository.
* The static block initializes the todos list with some predefined Todo objects. This ensures that the list is populated with sample data when the class is loaded.
* **getTodosByName(String name)**:
  * Retrieves all todos from the list. In this implementation, todos are not filtered by name; the method returns the complete list.
  * name is a parameter, but it is not used in the current implementation.
  * Returns the list of all todo items.
* **addTodo(String username, String description, LocalDate date, boolean completed)**:
  * Adds a new todo item to the list with the provided details.
  * This method does not return a value. It modifies the internal todos list by adding a new Todo item.
* **Key Points**:
  * **Data Storage**: This service uses an in-memory ArrayList to store todo items. For a real application, you would typically use a database or another persistent storage mechanism.
  * **Unique IDs**: Each todo item is assigned a unique ID by incrementing the id counter.
  * **Sample Data**: The static block provides initial data for testing purposes. In a real application, data would be added and retrieved from a database.

### Todo class :
```java
package com.ayush953.first_web_app.todo;

import java.time.LocalDate;

public class Todo {
    private int id;
    private String username;
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
```

### listTodos and addTodo jsp :
**listTodos.jsp** :
```html
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="\webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
    <title>All Todos</title>
</head>
<body>
<div class="container">
<h1>Welcome ${name}!</h1>
<hr>
<h1>Your Todos :</h1>
<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Target Date</th>
            <th>Completed</th>
        </tr>
    </thead>
    <tbody>
        `<c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.completed}</td>
            </tr>
        </c:forEach>`
    </tbody>
</table>
<a href="add-todo" class="btn btn-primary">Add Todo</a>
</div>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>
```
#### Explanation :
* **<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>**.
  This line declares the JSTL(JavaServer Pages Standard Tag Library) core library with the prefix c. It allows the use of JSTL tags like <c:forEach> for iterating over collections.

**addTodo.jsp** :
```html
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link href="\webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
    <title>Add Todo</title>
</head>
<body>
    <div class="container">
        <h1>Add Todo</h1>
        <hr>
        <form:form method="post" modelAttribute="todo">
            Description : <form:input type="text" path="description" required="required"/>
            <form:input type="hidden" path="id" />
            <form:input type="hidden" path="completed"/>
            <input type="submit" class="btn btn-success" />
        </form:form>
    </div>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>
```
#### Explanation :
* **<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>**.
Declares the JSTL core library for standard tags such as iteration and conditional processing.
* **<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>**.Declares the Spring form tag library, allowing the use of tags to generate and handle form elements and data binding.
* **modelAttribute="todo"**: Binds the form to a Todo object, allowing data entered in the form to be mapped to this object.
* **path="description"**: Binds the field to the description property of the Todo object.

## Validations with Spring boot

### Steps
1. Spring Boot Starter Validation
    * pom.xml
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
2. Command Bean (Form Backing Object)
    * 2-way binding(addTodo.jsp) & TodoController.java

**addTodo.jsp** :
```html
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link href="\webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
    <title>Add Todo</title>
</head>
<body>
    <div class="container">
        <h1>Add Todo</h1>
        <hr>
        <form:form method="post" modelAttribute="todo">
            Description : <form:input type="text" path="description" required="required"/>
            <form:input type="hidden" path="id" />
            <form:input type="hidden" path="completed"/>
            <input type="submit" class="btn btn-success" />
        </form:form>
    </div>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>
```
**TodoController.java** :
```java
@RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String newTodo(ModelMap map){
        String username = (String)map.get("name");
        Todo todo = new Todo(0,username,"",LocalDate.now(),false);
        map.put("todo",todo);
        return "addTodo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap map,Todo todo){
        String username = (String)map.get("name");
        todoService.addTodo(username,todo.getDescription(), LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";
    }
```
3. Add Validations to Bean
    * Todo.java

**Todo.java** :
```java
    @Size(min=10,message = "Enter atleast 10 characters")
    private String description;
```
For now, we are validating only description field.
We need to make some changes in TodoController class as well to handle the validation.

**TodoController.java** :
```java
@RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTodo";
        }
        String username = (String)map.get("name");
        todoService.addTodo(username,todo.getDescription(), LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";
    }
```
4. Display validation Errors in the View
    * addTodo.jsp
      
**addTodo.jsp** :
```html
 <form:errors path="description" class="text-warning" />
```

## Implementing Delete Todo functionality
Let's Add the delete button for each todo:

**listTodos.jsp**:
```html
`<c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.completed}</td>
                <td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a></td>
            </tr>
</c:forEach>`
```
**Now Let's implement a function in TodoService to delete the particular todo using its id.**

**TodoService.java** :
```java
public void deleteTodoById(int id){
        todos.removeIf(todo -> todo.getId() == id);
    }
```
we are using a lambda function to remove particular todo if its id matches the id which we want to delete.

Create a mapping for **delete-todo** endpoint to delete the todo:

**TodoController.java**:
```java
@RequestMapping("/delete-todo")
public String deleteTodo(@RequestParam int id){
    todoService.deleteTodoById(id);
    return "redirect:list-todos";
}
```

## Implementing Update Todo functionality

Let's Add the delete button for each todo:

**listTodos.jsp**:
```html
`<c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.completed}</td>
                <td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a></td>
              <td><a href="update-todo?id=${todo.id}" class="btn btn-dark">Update</a></td>
            </tr>
</c:forEach>`
```

**Let's implement a function to find the todo by its id**:

**TodoService.java** :
```java
public Todo findById(int id){
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }
```
Create a mapping for **update-todo** endpoint to show the page to update the todo:

**TodoController.java**:
```java
@RequestMapping("/update-todo")
public String showUpdateTodo(@RequestParam int id, ModelMap map){
  Todo todo = todoService.findById(id);
  map.put("todo",todo);
  return "addTodo";
}
```