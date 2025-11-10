package model;

import model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Project {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private String description;

    // Constructor privado
    private Project(UUID id, String name, LocalDate startDate, LocalDate endDate,
                    ProjectStatus status, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    // Factory method con validaciones
    public static Project create(String name, LocalDate startDate, LocalDate endDate,
                                 ProjectStatus status, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        if (endDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("End date must be today or later");
        }

        return new Project(UUID.randomUUID(), name, startDate, endDate, status, description);
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public ProjectStatus getStatus() { return status; }
    public String getDescription() { return description; }

    // Métodos específicos para actualizar (sin setters genéricos)
    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        this.name = newName;
    }

    public void updateDates(LocalDate newStart, LocalDate newEnd) {
        if (newEnd.isBefore(newStart)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.startDate = newStart;
        this.endDate = newEnd;
    }

    public void updateStatus(ProjectStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}