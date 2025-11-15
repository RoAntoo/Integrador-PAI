package output;

import model.Task;

import java.util.Optional;

public interface ITaskRepository {
    Task save(Task task);
    boolean existsById(Long id);
    Optional<Task> findById(Long id);
}