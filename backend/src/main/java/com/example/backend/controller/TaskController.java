package com.example.backend.controller;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import com.example.backend.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService repo){
        this.service = repo;
    }

    @GetMapping
    public List<Task> getTasks(){
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return service.createTask(task.getTitle());
    }

    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable long id){
        return  service.toggleStatus(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable long id){
        return service.deleteTask(id);
    }
}
