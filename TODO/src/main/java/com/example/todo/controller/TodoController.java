package com.example.todo.controller;

import com.example.todo.dto.TodoCreateRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.TodoUpdateRequestDTO;
import com.example.todo.dto.TodoUpdateStatusDTO;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/todo")
    public Todo create(@RequestBody TodoCreateRequestDTO todo) {
        return todoService.create(todo);
    }

    @GetMapping("/todo")
    public List<TodoResponseDTO> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/todo/id/{id}")
    public TodoResponseDTO findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @GetMapping("/todo/title/{title}")
    public TodoResponseDTO findByTitle(@PathVariable String title) {
        return todoService.findByTitle(title);
    }

    @GetMapping("/todo/content/{content}")
    public TodoResponseDTO findByContent(@PathVariable String content) {
        return todoService.findByContent(content);
    }

    @GetMapping("/todo/completed/true")
    public TodoResponseDTO findByCompletedTrue() {
        return todoService.findByCompletedTrue();
    }

    @GetMapping("/todo/completed/false")
    public TodoResponseDTO findByCompletedFalse() {
        return todoService.findByCompletedFalse();
    }

    @GetMapping("/todo/deadline/before/{deadline}")
    public TodoResponseDTO findByDeadlineBefore(@PathVariable LocalDate deadline) {
        return todoService.findByDeadlineBefore(deadline);
    }

    @GetMapping("/todo/deadline/after/{deadline}")
    public TodoResponseDTO findByDeadlineAfter(@PathVariable LocalDate deadline) {
        return todoService.findByDeadlineAfter(deadline);
    }

    @GetMapping("/todo/deadline/{startDate}/{deadline}")
    public TodoResponseDTO findByDeadlineBetween(@PathVariable LocalDate startDate, LocalDate deadline) {
        return todoService.findByDeadlineBetween(startDate, deadline);
    }

    @PatchMapping("/todo/{id}")
    public void update(@PathVariable Long id, @RequestBody TodoUpdateRequestDTO todo) {
        todoService.update(id, todo);
    }

    @PatchMapping("/todo/completed/{id}")
    public void updateCompleted(@PathVariable Long id, @RequestBody TodoUpdateStatusDTO todo) {
        todoService.updateCompleted(id, todo);
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }






}