package me.harish.task.mappers;

import me.harish.task.domain.dto.TaskDto;
import me.harish.task.domain.entities.Task;

public interface TaskMapper {

   Task fromDto(TaskDto taskDto);

   TaskDto toDto(Task task);
}
