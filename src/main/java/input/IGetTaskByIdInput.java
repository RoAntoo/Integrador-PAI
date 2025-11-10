package input;

import model.Task;

public interface IGetTaskByIdInput {
    Task getTaskById(Long id);
}