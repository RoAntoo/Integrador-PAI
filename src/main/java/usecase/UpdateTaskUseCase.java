package usecase;

import exception.ResourceNotFoundException;
import exception.BusinessRuleViolationException;
import input.IUpdateTaskInput;
import input.DTO.UpdateTaskDTO;
import model.Task;
import output.ITaskRepository;

public class UpdateTaskUseCase implements IUpdateTaskInput {

    private final ITaskRepository taskRepository;
    public UpdateTaskUseCase(ITaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task updateTask(Long id, UpdateTaskDTO dto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found."));

        if (dto.title() != null && !dto.title().isBlank()) {
            existingTask.setTitle(dto.title());
        }

        if (dto.estimateHours() != null) {
            if (dto.estimateHours() <= 0) {
                throw new BusinessRuleViolationException("Estimate hours must be greater than 0");
            }
            existingTask.setEstimateHours(dto.estimateHours());
        }

        if (dto.assignee() != null) {
            existingTask.setAssignee(dto.assignee());
        }

        return taskRepository.save(existingTask);
    }
}