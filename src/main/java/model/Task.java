package model;

import model.enums.ProjectStatus;
import model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private UUID id;
    private Project project;
    private String title;
    private Integer estimateHours;
    private String assignee;
    private TaskStatus status;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;

    // Constructor privado
    private Task(UUID id, Project project, String title, Integer estimateHours,
                 String assignee, TaskStatus status, LocalDateTime finishedAt, LocalDateTime createdAt) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
    }

    // Factory method
    public static Task create(Project project, String title, Integer estimateHours,
                              String assignee, TaskStatus status) {
        if (project == null) {
            throw new IllegalArgumentException("Project is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title is required");
        }
        if (estimateHours == null || estimateHours <= 0) {
            throw new IllegalArgumentException("Estimate hours must be greater than 0");
        }
        if (project.getStatus() == ProjectStatus.CLOSED) {
            throw new IllegalArgumentException("Cannot add a task to a CLOSED project");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime finishedAt = (status == TaskStatus.DONE) ? now : null;

        return new Task(UUID.randomUUID(), project, title, estimateHours, assignee, status, finishedAt, now);
    }

    // Getters
    public UUID getId() { return id; }
    public Project getProject() { return project; }
    public String getTitle() { return title; }
    public Integer getEstimateHours() { return estimateHours; }
    public String getAssignee() { return assignee; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getFinishedAt() { return finishedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setEstimateHours(Integer estimateHours) { this.estimateHours = estimateHours; }
    public void setAssignee(String assignee) { this.assignee = assignee; }
    public void setStatus(TaskStatus status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}