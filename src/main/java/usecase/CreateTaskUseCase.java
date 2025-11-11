package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import input.ITaskInput;
import model.Project;
import model.Task;
import model.enums.ProjectStatus;
import model.enums.TaskStatus;
import output.IProjectRepository;
import output.ITaskRepository;

import java.time.Clock;
import java.time.LocalDateTime;

public class CreateTaskUseCase implements ITaskInput {

    private final IProjectRepository projectRepository;
    private final ITaskRepository taskRepository;
    private final Clock clock;

    public CreateTaskUseCase(IProjectRepository projectRepository,
                             ITaskRepository taskRepository,
                             Clock clock) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.clock = clock;
    }

    @Override
    public Task createTask(Long projectId, String title,
                           Integer estimateHours, String assignee,
                           TaskStatus status) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new
                        ResourceNotFoundException("Project not found with id: " + projectId));

        if (project.getStatus().equals(ProjectStatus.CLOSED)) {
            throw new BusinessRuleViolationException("Cannot add a task to a CLOSED project");
        }

        LocalDateTime now = LocalDateTime.now(clock);

        Task task = Task.create(project, title, estimateHours, assignee, status, now);

        return taskRepository.save(task);
    }
}