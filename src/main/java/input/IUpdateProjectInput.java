package input;

import input.DTO.UpdateProjectDTO;
import model.Project;

public interface IUpdateProjectInput {
    Project updateProject(Long projectId, UpdateProjectDTO dto);
}
