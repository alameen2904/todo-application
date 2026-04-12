package com.application.todo;

import com.application.todo.models.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service // Tells Spring to manage this class
public class TodoService {

    // 1. Declare the dependency
    @Autowired
    private TodoRepository todoRepository;
    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo Not Found"));
    }
    // Fixed "page" to "Page"
    public Page<Todo> getAllTodosPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }


    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    public void deleteTodoById(Long id) {
        todoRepository.delete(getTodoById(id));
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll(); // This is a built-in JPA method!
    }

}