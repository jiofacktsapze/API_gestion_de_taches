package com.jiofack.todoapp.repository;

import com.jiofack.todoapp.models.Task;
import com.jiofack.todoapp.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);

}
