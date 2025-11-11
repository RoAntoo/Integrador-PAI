package output;

import model.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    void deleteById(Long id);
    List<Task> getAllTasks();
    boolean existsById(Long id);
}