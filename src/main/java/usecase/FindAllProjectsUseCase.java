package usecase;

import input.IFindAllProjectsInput;
import input.DTO.FindProjectsQueryDTO;
import model.Project;
import output.IProjectRepository;

import java.util.List;

public class FindAllProjectsUseCase implements IFindAllProjectsInput {

    private final IProjectRepository projectRepository;

    public FindAllProjectsUseCase(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAllProjects(FindProjectsQueryDTO queryDTO) {
        return projectRepository.findWithFilters(queryDTO);
    }
}