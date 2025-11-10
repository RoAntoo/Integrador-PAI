package input;

import model.Task;
import java.util.UUID;

public interface IDeleteTaskInput {
    Task deleteTaskById(UUID id);
}
