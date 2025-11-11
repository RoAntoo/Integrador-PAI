package input.DTO;

public record UpdateTaskDTO(
        String title,
        Integer estimateHours,
        String assignee
) {}
