package usecase;

import input.IDeleteTaskInput;
import output.ITaskRepository;
import exception.ResourceNotFoundException;
import model.Task;

import java.util.UUID;

public class DeleteTaskUseCase implements IDeleteTaskInput {

    private final ITaskRepository taskRepository;

    public DeleteTaskUseCase(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task deleteTaskById(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task with ID " + id + " not found.");
        }
        return taskRepository.deleteById(id);
    }
}