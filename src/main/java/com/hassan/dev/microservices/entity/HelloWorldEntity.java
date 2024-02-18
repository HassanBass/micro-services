package com.hassan.dev.microservices.entity;

public class HelloWorldEntity {
    private String message;

    public HelloWorldEntity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
