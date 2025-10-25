package com.example.backend.service;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> getAllTasks(){
        return repo.findAll();
    }

    public Task createTask(String title){
        Task task = new Task(0,title,false);
        return repo.save(task);
    }

    public Task toggleStatus(long id){
        return repo.toggleStatus(id);
    }

    public boolean deleteTask(long id){
        return repo.deleteById(id);
    }
}
