package input.DTO;

import java.time.LocalDate;

public record UpdateProjectDTO(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String description
) {}