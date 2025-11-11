package model;

import model.enums.ProjectStatus;
import model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private Long id;
    private Project project;
    private String title;
    private Integer estimateHours;
    private String assignee;
    private TaskStatus status;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;

    // Constructor privado
    private Task(Long id, Project project, String title, Integer estimateHours,
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

    public static Task create(Project project, String title, Integer estimateHours,
                              String assignee, TaskStatus status, LocalDateTime createdAtTimestamp) {
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

        LocalDateTime finishedAt = (status == TaskStatus.DONE) ? createdAtTimestamp : null;

        return new Task(null, project, title, estimateHours, assignee, status, finishedAt, createdAtTimestamp);
    }

    // Getters
    public Long getId() { return id; }
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
    protected void setFinishedAt(LocalDateTime finishedAt) { this.finishedAt = finishedAt; }

    public void setStatus(TaskStatus newStatus, LocalDateTime timestamp) {
        if (newStatus == TaskStatus.DONE && this.status != TaskStatus.DONE) {
            this.finishedAt = timestamp; //
        }
        else if (newStatus != TaskStatus.DONE) {
            this.finishedAt = null;
        }

        this.status = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}