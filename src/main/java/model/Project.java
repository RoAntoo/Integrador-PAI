package model;

import model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.Objects;

public class Project {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private String description;

    private Project(Long id, String name, LocalDate startDate, LocalDate endDate, // CAMBIO: id es Long
                    ProjectStatus status, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    public static Project create(String name, LocalDate startDate, LocalDate endDate,
                                 ProjectStatus status, String description,
                                 LocalDate today) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        if (endDate.isBefore(today)) {
            throw new IllegalArgumentException("End date must be today or later");
        }

        return new Project(null, name, startDate, endDate, status, description);
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public ProjectStatus getStatus() { return status; }
    public String getDescription() { return description; }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}