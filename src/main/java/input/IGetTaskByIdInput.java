package input;

import model.Task;

import java.util.Optional;
import java.util.UUID;

public interface IGetTaskByIdInput {
    Optional<Task> getTaskById(UUID id);
}
