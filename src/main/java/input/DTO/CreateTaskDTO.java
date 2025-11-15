package input.DTO;

import model.enums.TaskStatus;

public record CreateTaskDTO(
        String title,
        Integer estimateHours,
        String assignee,
        TaskStatus status
) {
}