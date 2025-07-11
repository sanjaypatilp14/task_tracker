package me.harish.task.services.impl;

import jakarta.transaction.Transactional;
import me.harish.task.domain.entities.Task;
import me.harish.task.domain.entities.TaskList;
import me.harish.task.domain.entities.TaskPriority;
import me.harish.task.domain.entities.TaskStatus;
import me.harish.task.repositories.TaskListRepository;
import me.harish.task.repositories.TaskRepositoy;
import me.harish.task.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepositoy taskRepositoy;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepositoy taskRepositoy, TaskListRepository taskListRepository) {
        this.taskRepositoy = taskRepositoy;
        this.taskListRepository = taskListRepository;
    }

    LocalDateTime now = LocalDateTime.now();

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepositoy.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an Id");
        }

        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList=taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));


        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );
        return taskRepositoy.save(taskToSave);


    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepositoy.findByTaskListIdAndId(taskListId,taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()){
            throw new IllegalArgumentException("Task must have an ID");
        }

        if(!Objects.equals(taskId,task.getId())){
            throw new IllegalArgumentException("Task IDs do not match");
        }

        if(null == task.getPriority()){
            throw  new IllegalArgumentException("Task should have a proirity");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task should have a valid status");
        }

        Task existingTask = taskRepositoy.findByTaskListIdAndId(taskListId,taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepositoy.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepositoy.deleteByTaskListIdAndId(taskListId,taskId);
    }


}
