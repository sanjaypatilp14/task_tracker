package me.harish.task.services;

import me.harish.task.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(Task task);
    Optional<Task> getTask(UUID id);
    Task updateTask (UUID taskId, Task task);
    void deleteTask (UUID tasKId);
}
