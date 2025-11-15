package output;

import input.DTO.FindProjectsQueryDTO;
import model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    // --- metodos que JpaRepository ya da ---
    // save(Project project)
    // findById(Long id)
    // findAll()

    Optional<Project> findByName(String name);

    boolean existsByName(String name);

    List<Project> findWithFilters(FindProjectsQueryDTO queryDTO);
}