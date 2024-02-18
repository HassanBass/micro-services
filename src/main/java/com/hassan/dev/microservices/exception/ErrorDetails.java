package com.hassan.dev.microservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private final LocalDateTime time;
    private final String message;
    private final String description;

    public ErrorDetails(LocalDateTime time, String message, String description) {
        this.time = time;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
