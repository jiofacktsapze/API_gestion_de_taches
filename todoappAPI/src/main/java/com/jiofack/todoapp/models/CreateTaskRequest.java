package com.jiofack.todoapp.models;

import lombok.Data;

@Data
public class CreateTaskRequest {

    private String title;

    private String description;
}
