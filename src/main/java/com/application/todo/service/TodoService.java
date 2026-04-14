package com.application.todo.service;

import com.application.todo.models.Todo;
import com.application.todo.models.User;
import com.application.todo.repository.TodoRepository;
import com.application.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Todo> getTodosByUser(String email) {
        return todoRepository.findByUserEmail(email);
    }


    public Todo createTodo(Todo todo, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        todo.setUser(user);
        return todoRepository.save(todo);
    }


    public Todo getTodoById(long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }
    public Todo updateTodo(Todo todo) {


        Todo existing = todoRepository.findById(todo.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found"));


        existing.setTitle(todo.getTitle());
        existing.setIsCompleted(todo.getIsCompleted());


        return todoRepository.save(existing);
    }
    public void deleteTodoById(long id) {
        todoRepository.deleteById(id);
    }
}