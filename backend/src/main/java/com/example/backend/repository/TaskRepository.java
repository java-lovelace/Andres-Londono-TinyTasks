package com.example.backend.repository;

import com.example.backend.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public TaskRepository() {
        tasks.add(new Task(nextId++, "Estudiar Spring Boot", false));
        tasks.add(new Task(nextId++, "Crear frontend simple", true));
    }

    public List<Task> findAll(){
        return tasks;
    }

    public Task save(Task task) {
        task.setId(nextId++);
        tasks.add(task);

        return task;
    }

    public Task toggleStatus(long id){
        for (Task t: tasks){
            if (t.getId() == id){
                t.setDone(!t.isDone());
                return t;
            }
        }
        return null;
    }

    public boolean deleteById(long id){
        return tasks.removeIf(t -> t.getId() == id);
    }
}
