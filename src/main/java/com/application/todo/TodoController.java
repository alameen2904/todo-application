package com.application.todo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo") // Added a base path for better organization
public class TodoController {

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
    public String createUser(@RequestBody String body) {
        return body;
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