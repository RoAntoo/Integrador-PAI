package input;

import input.DTO.FindProjectsQueryDTO;
import model.Project;
import java.util.List;

public interface IFindAllProjectsInput {
    List<Project> findAllProjects(FindProjectsQueryDTO queryDTO);
}