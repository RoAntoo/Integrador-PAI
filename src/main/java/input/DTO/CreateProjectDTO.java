package input.DTO;

import model.enums.ProjectStatus;
import java.time.LocalDate;

public record CreateProjectDTO(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        ProjectStatus status,
        String description
) {
}