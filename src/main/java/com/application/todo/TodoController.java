package com.application.todo;

import com.application.todo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo") // Added a base path for better organization
public class TodoController {
    @Autowired
    private TodoService todoService;

    // Get all
    @GetMapping("/get")
    public String getTodo() {

        return "Todo";
    }

    // Path Variable
    @GetMapping("/{id}")
    public String getTodoById(@PathVariable long id) {
        return "Todo with Id " + id;
    }

    // Request Param
    @GetMapping("/search")
    public String getTodoByIdParam(@RequestParam("todoId") long id) {
        return "Todo with Id " + id;
    }

    // Post mapping
    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo){
   return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);

    }

    // Put mapping
    @PutMapping("/{id}")
    public String updateTodoById(@PathVariable long id) {
        return "Update Todo with Id " + id;
    }

    // Delete mapping
    @DeleteMapping("/{id}")
    public String deleteTodoById(@PathVariable long id) {
        return "Delete Todo with Id " + id;
    }
}