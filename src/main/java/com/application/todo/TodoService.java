package com.application.todo;

import com.application.todo.models.Todo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service // Tells Spring to manage this class
public class TodoService {

    // 1. Declare the dependency
    @Autowired
    private TodoRepository todoRepository;
    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

}