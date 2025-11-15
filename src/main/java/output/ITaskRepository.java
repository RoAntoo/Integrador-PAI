package output;

import model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    // --- estos metodos que jpa ya tiene implementado(buscar mas) ---
    // save(Task task)
    // findById(Long id)
    // deleteById(Long id)
    // existsById(Long id)

    List<Task> findAll();
}