package com.jiofack.todoapp.models;

import lombok.Data;

@Data
public class UpdateTaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
}
