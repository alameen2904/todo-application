package com.application.todo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service // Tells Spring to manage this class
public class TodoService {

    // 1. Declare the dependency
    @Autowired
    private TodoRepository todoRepository;



    // 3. Logic to print the data
    public void printTodos() {
        System.out.println(todoRepository.getAllTodos());
    }
}