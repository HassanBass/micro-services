package com.hassan.dev.microservices.controller;

import com.hassan.dev.microservices.dao.UserDaoService;
import com.hassan.dev.microservices.entity.HelloWorldEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-world")
public class HelloWorldController {



    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/entity")
    public HelloWorldEntity helloWorldBean(){
        return new HelloWorldEntity("Hello World Entity");
    }
    @GetMapping("/path-variable/{name}")
    public String helloWorldPathVariable(@PathVariable("name") String newName){
        return ("Hello "+newName);
    }
}
