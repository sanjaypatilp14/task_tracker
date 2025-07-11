package me.harish.task.services.impl;

import me.harish.task.domain.entities.Task;
import me.harish.task.repositories.TaskRepositoy;
import me.harish.task.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepositoy taskRepositoy;

    public TaskServiceImpl(TaskRepositoy taskRepositoy) {
        this.taskRepositoy = taskRepositoy;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepositoy.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(Task task) {
        return null;
    }

    @Override
    public Optional<Task> getTask(UUID id) {
        return Optional.empty();
    }

    @Override
    public Task updateTask(UUID taskId, Task task) {
        return null;
    }

    @Override
    public void deleteTask(UUID tasKId) {

    }
}
