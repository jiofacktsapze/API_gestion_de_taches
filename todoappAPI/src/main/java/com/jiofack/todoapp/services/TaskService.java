package com.jiofack.todoapp.services;

import com.jiofack.todoapp.exceptions.TaskNotFoundException;
import com.jiofack.todoapp.models.Task;
import com.jiofack.todoapp.models.TaskStatus;
import com.jiofack.todoapp.models.UpdateTaskRequest;
import com.jiofack.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

   private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task createTask(String title, String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(TaskStatus.TO_DO); // toute nouvelle tâche commence par à faire
        return taskRepository.save(task); // retourne la tâche créée
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.deleteById(id);
    }

    public Task updateTaskStatus(Long id, TaskStatus newStatus) {
        Task task = getTaskById(id);
        TaskStatus currentStatus = task.getStatus();

        if (newStatus == null){
            throw new IllegalArgumentException("Le statut ne peut pas être nul.");
        }

        //Empêche les transitions illogiques
        if (!isTransitionAllowed(currentStatus, newStatus)){
            throw new IllegalArgumentException(
                    String.format("Transition invalide de %s vers %s", currentStatus, newStatus)
            );
        }
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    private Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Task updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
        return taskRepository.save(task);
    }

    private boolean isTransitionAllowed(TaskStatus current, TaskStatus next){
        return switch (current){
            case TO_DO -> next == TaskStatus.IN_PROGRESS;
            case IN_PROGRESS -> next == TaskStatus.DONE;
            case DONE -> false;
        };
    }
}
