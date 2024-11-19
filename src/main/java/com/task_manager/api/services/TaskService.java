package com.task_manager.api.services;

import com.task_manager.api.models.Task;
import com.task_manager.api.repos.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task taskInfo) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        task.setTitle(taskInfo.getTitle());
        task.setDescription(taskInfo.getDescription());
        task.setStatus(taskInfo.getStatus());
        task.setPriority(taskInfo.getPriority());
        task.setComments(taskInfo.getComments());
        task.setAuthor(task.getAuthor());
        task.setExecutor(taskInfo.getExecutor());

        return taskRepository.save((task));
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
