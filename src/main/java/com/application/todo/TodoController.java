package com.application.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @GetMapping("/hello")
    String sayHelloWorld(){
        return "Hello world!";
    }
}
