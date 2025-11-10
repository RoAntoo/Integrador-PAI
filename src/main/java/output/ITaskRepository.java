package output;

import model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    boolean existsById(UUID id);
    Task deleteById(UUID id);
    Optional<Task> getTaskById(UUID id);

    List<Task> getAllTasks();
}

