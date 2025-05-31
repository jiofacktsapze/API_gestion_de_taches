package com.jiofack.todoapp.controller;

import com.jiofack.todoapp.models.CreateTaskRequest;
import com.jiofack.todoapp.models.Task;
import com.jiofack.todoapp.models.TaskStatus;
import com.jiofack.todoapp.models.UpdateTaskRequest;
import com.jiofack.todoapp.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Lire toutes les tâches et filtrer par status")
    public List<Task> getTasks(@RequestParam(value ="status", required = false) TaskStatus status) {
        if (status != null) {
            return taskService.getTasksByStatus(status); // Appel pour filtrer les tâches
        } else {
            return taskService.getAllTasks(); //Appel pour récupérer toutes les tâches
        }
    }

    @PostMapping// Création d'une tâche avec titre et description
    @Operation(summary = "Créer une nouvelle tâche")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tâche créée"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request){
            Task createdTask = taskService.createTask(request.getTitle(), request.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)// Suppression d'une tâche par ID
    @Operation(summary = "Supprimer une tâche par son ID")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/status") // Changement de status de la tâche (toggle)
    @Operation(summary = "Changer le status d'une tâche")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status){
        Task updatedTask = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}")// Mise à jour d'une tâche
    @Operation(summary = "Mettre à jour une tâche(Modifier le titre et/ou la description)")
    public  ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        Task updatedTask = taskService.updateTask(id, request);
        return ResponseEntity.ok(updatedTask);
    }
}
