package com.pa.proyecto.backend_integrator.adapter.web;

import input.DTO.CreateProjectDTO;
import input.DTO.UpdateProjectDTO;
import model.Project;
import usecase.CreateProjectUseCase;
import usecase.UpdateProjectUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    // private final FindAllProjectsUseCase findAllProjectsUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase,
                             UpdateProjectUseCase updateProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
    }

    // --- ENDPOINTS ---

    /**
     * Endpoint para crear un proyecto.
     * HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody CreateProjectDTO dto) {
        Project createdProject = createProjectUseCase.createProject(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    /**
     * Endpoint para actualizar un proyecto.
     * HTTP 200 OK
     */
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestBody UpdateProjectDTO dto) {

        Project updatedProject = updateProjectUseCase.updateProject(projectId, dto);
        return ResponseEntity.ok(updatedProject);
    }


}