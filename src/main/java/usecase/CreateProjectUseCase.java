package usecase;

import exception.BusinessRuleViolationException;
import exception.DuplicateResourceException;
import input.ICreateProjectInput;
import input.DTO.CreateProjectDTO;
import model.Project;
import output.IProjectRepository;

import java.time.Clock;
import java.time.LocalDate;

public class CreateProjectUseCase implements ICreateProjectInput {

    private final IProjectRepository projectRepository;
    private final Clock clock;

    public CreateProjectUseCase(IProjectRepository projectRepository, Clock clock) {
        this.projectRepository = projectRepository;
        this.clock = clock;
    }

    @Override
    public Project createProject(CreateProjectDTO dto) {

        if (projectRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Project with name '" + dto.name() + "' already exists.");
        }

        if (dto.endDate().isBefore(dto.startDate())) {
            throw new BusinessRuleViolationException("Project end date must be after start date.");
        }

        LocalDate today = LocalDate.now(clock);
        if (dto.endDate().isBefore(today)) {
            throw new BusinessRuleViolationException("Project end date must be today or in the future.");
        }

        Project newProject = Project.create(
                dto.name(),
                dto.startDate(),
                dto.endDate(),
                dto.status(),
                dto.description(),
                today
        );

        return projectRepository.save(newProject);
    }
}