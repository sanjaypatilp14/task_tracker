package me.harish.task.mappers;

import me.harish.task.domain.dto.TaskListDto;
import me.harish.task.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
