package output;

import input.DTO.FindProjectsQueryDTO;
import model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository extends JpaRepository<Project, Long> {
    Project save(Project project);
    Optional<Project> findById(Long id);
    Optional<Project> findByName(String name);
    Boolean existsByName(String name);
    List<Project> findWithFilters(FindProjectsQueryDTO queryDTO);
}