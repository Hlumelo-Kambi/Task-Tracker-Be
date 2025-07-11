package com.devtiro.tasks.mappers;

import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.dto.TaskListDto;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
