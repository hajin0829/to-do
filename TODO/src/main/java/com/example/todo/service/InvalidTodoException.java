package com.example.todo.service;

public class InvalidTodoException extends RuntimeException{
    public InvalidTodoException(String message) {
        super(message);
    }
}