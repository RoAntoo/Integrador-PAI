package input.DTO;

import model.enums.ProjectStatus;
import java.time.LocalDate;
import java.util.Optional;

public record FindProjectsQueryDTO(
        Optional<ProjectStatus> status,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate
) {
    public static FindProjectsQueryDTO empty() {
        return new FindProjectsQueryDTO(Optional.empty(), Optional.empty(), Optional.empty());
    }
}