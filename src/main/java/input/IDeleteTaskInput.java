package input;

import model.Task;

public interface IDeleteTaskInput {
    Task deleteTaskById(Long id);
}
