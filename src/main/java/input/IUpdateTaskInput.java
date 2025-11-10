package input;

import input.DTO.UpdateTaskDTO;
import model.Task;

public interface IUpdateTaskInput {
    Task updateTask(Long id, UpdateTaskDTO dto);
}