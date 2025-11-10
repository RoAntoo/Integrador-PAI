package usecase;

import input.IDeleteTaskInput;
import output.ITaskRepository;
import exception.ResourceNotFoundException;
import model.Task;

public class DeleteTaskUseCase implements IDeleteTaskInput {

    private final ITaskRepository taskRepository;

    public DeleteTaskUseCase(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task deleteTaskById(Long id) {
        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found."));

        taskRepository.deleteById(id);

        return taskToDelete;
    }
}