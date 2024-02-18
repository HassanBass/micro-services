package com.hassan.dev.microservices.controller;

import com.hassan.dev.microservices.dao.UserDaoService;
import com.hassan.dev.microservices.entity.HelloWorldEntity;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("hello-world")
public class HelloWorldController {


    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping("/internationalization")
    public String helloWorldInternationalization(){
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage("hello.world", null, "Default Message", locale);
        //return "Hello World V2";
    }
}
