package usecase;

import input.IGetTaskInput;
import model.Task;
import output.ITaskRepository;

import java.util.ArrayList;
import java.util.List;

public class GetTasks implements IGetTaskInput {
    private final ITaskRepository taskRepository;

    public GetTasks(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        if (tasks.isEmpty()) {
            return List.of();
        }
        return tasks;
    }
}
