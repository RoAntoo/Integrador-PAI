package input;

import model.Task;
import model.enums.TaskStatus;

public interface ITaskInput {
    Task createTask(Long projectId, String title, Integer estimateHours, String assignee, TaskStatus status);
}

