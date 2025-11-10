package usecase;

import exception.BusinessRuleViolationException;
import exception.DuplicateResourceException;
import exception.ResourceNotFoundException;
import input.DTO.UpdateProjectDTO;
import model.Project;
import model.enums.ProjectStatus;
import output.IProjectRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProjectUseCaseTest {

    @Mock
    private IProjectRepository projectRepository;

    @InjectMocks
    private UpdateProjectUseCase updateProjectUseCase;

    private Project existingProject;
    private Long projectId;

    @BeforeEach
    void setUp() {
        projectId = 1L;
        existingProject = Project.create(
                "Proyecto Original",
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 20),
                ProjectStatus.ACTIVE,
                "Desc original",
                LocalDate.of(2025, 1, 1) // today
        );
    }

    @Test
    void testUpdateProject_HappyPath_AllFields() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepository.existsByName("Nuevo Nombre")).thenReturn(false);
        when(projectRepository.save(any(Project.class))).thenReturn(existingProject);

        UpdateProjectDTO dto = new UpdateProjectDTO(
                "Nuevo Nombre",
                LocalDate.of(2025, 1, 12),
                LocalDate.of(2025, 1, 22),
                "Nueva Desc"
        );

        Project result = updateProjectUseCase.updateProject(projectId, dto);

        assertNotNull(result);
        assertEquals("Nuevo Nombre", result.getName());
        assertEquals(LocalDate.of(2025, 1, 12), result.getStartDate());
        assertEquals(LocalDate.of(2025, 1, 22), result.getEndDate());
        assertEquals("Nueva Desc", result.getDescription());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).existsByName("Nuevo Nombre");
        verify(projectRepository, times(1)).save(existingProject);
    }

    @Test
    void testUpdateProject_HappyPath_PartialUpdate() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any(Project.class))).thenReturn(existingProject);

        UpdateProjectDTO dto = new UpdateProjectDTO(
                null, // No se actualiza nombre
                null, // No se actualiza start date
                LocalDate.of(2025, 1, 25), // Se actualiza end date
                null  // No se actualiza desc
        );

        Project result = updateProjectUseCase.updateProject(projectId, dto);

        assertEquals("Proyecto Original", result.getName());
        assertEquals(LocalDate.of(2025, 1, 10), result.getStartDate());
        assertEquals(LocalDate.of(2025, 1, 25), result.getEndDate());
        verify(projectRepository, never()).existsByName(anyString());
        verify(projectRepository, times(1)).save(existingProject);
    }

    @Test
    void testUpdateProject_Fails_WhenProjectNotFound() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        UpdateProjectDTO dto = new UpdateProjectDTO("Nuevo", null, null, null);

        assertThrows(ResourceNotFoundException.class, () -> {
            updateProjectUseCase.updateProject(projectId, dto);
        });

        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void testUpdateProject_Fails_WhenNameIsDuplicate() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepository.existsByName("Nombre Duplicado")).thenReturn(true);

        UpdateProjectDTO dto = new UpdateProjectDTO("Nombre Duplicado", null, null, null);

        assertThrows(DuplicateResourceException.class, () -> {
            updateProjectUseCase.updateProject(projectId, dto);
        });

        verify(projectRepository, times(1)).existsByName("Nombre Duplicado");
        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void testUpdateProject_Fails_WhenEndDateIsBeforeStartDate() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));

        UpdateProjectDTO dto = new UpdateProjectDTO(
                null,
                LocalDate.of(2025, 1, 30),
                LocalDate.of(2025, 1, 29),
                null
        );

        assertThrows(BusinessRuleViolationException.class, () -> {
            updateProjectUseCase.updateProject(projectId, dto);
        });

        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void testUpdateProject_Fails_WhenMixingOldAndNewDatesAreInvalid() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));

        // El StartDate (10-Ene) se mantiene, pero el nuevo EndDate (9-Ene) es inválido
        UpdateProjectDTO dto = new UpdateProjectDTO(
                null,
                null,
                LocalDate.of(2025, 1, 9),
                null
        );

        assertThrows(BusinessRuleViolationException.class, () -> {
            updateProjectUseCase.updateProject(projectId, dto);
        });

        verify(projectRepository, never()).save(any(Project.class));
    }
}