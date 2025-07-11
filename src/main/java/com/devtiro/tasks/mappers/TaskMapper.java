package com.devtiro.tasks.mappers;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.dto.TaskDto;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto (Task task);
}
