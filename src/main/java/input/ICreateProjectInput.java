package input;

import input.DTO.CreateProjectDTO;
import model.Project;

public interface ICreateProjectInput {
    Project createProject(CreateProjectDTO dto);
}