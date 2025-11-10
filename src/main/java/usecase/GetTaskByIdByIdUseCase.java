package usecase;

import exception.ResourceNotFoundException;
import input.IGetTaskByIdInput;
import model.Task;
import output.ITaskRepository;

public class GetTaskByIdByIdUseCase implements IGetTaskByIdInput {
    private final ITaskRepository taskRepository;

    public GetTaskByIdByIdUseCase(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Task with ID " + id + " not found."));
    }
}