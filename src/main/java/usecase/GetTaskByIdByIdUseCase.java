package usecase;

import exception.ResourceNotFoundException;
import input.IGetTaskByIdInput;
import model.Task;
import output.ITaskRepository;

import java.util.Optional;
import java.util.UUID;

public class GetTaskByIdByIdUseCase implements IGetTaskByIdInput {
    private final ITaskRepository taskRepository;

    public GetTaskByIdByIdUseCase(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> getTaskById(UUID id) {
        Optional<Task> taskOptional = this.taskRepository.getTaskById(id);

        if (!taskOptional.isPresent()) {
            throw new ResourceNotFoundException("Task with ID " + id + " not found.");
        }

        return taskOptional;
    }
}
