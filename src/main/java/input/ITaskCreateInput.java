package input;

import input.DTO.CreateTaskDTO;
import model.Task;

public interface ITaskCreateInput {
    Task createTask(Long projectId, CreateTaskDTO dto);
}