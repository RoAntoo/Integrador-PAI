package usecase;

import com.pa.proyecto.backend_integrator.core.input.DTO.FindProjectsQueryDTO;
import com.pa.proyecto.backend_integrator.core.model.Project;
import com.pa.proyecto.backend_integrator.core.model.enums.ProjectStatus;
import com.pa.proyecto.backend_integrator.core.output.IProjectRepository;

import com.pa.proyecto.backend_integrator.core.usecase.FindAllProjectsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllProjectsUseCaseTest {

    @Mock
    private IProjectRepository projectRepository;

    @InjectMocks
    private FindAllProjectsUseCase findAllProjectsUseCase;

    @Test
    void testFindAllProjects_NoFilters() {
        FindProjectsQueryDTO query = FindProjectsQueryDTO.empty();

        Project p1 = mock(Project.class);
        Project p2 = mock(Project.class);
        List<Project> mockList = List.of(p1, p2);

        when(projectRepository.findWithFilters(query)).thenReturn(mockList);

        List<Project> result = findAllProjectsUseCase.findAllProjects(query);

        assertEquals(2, result.size());
        assertSame(mockList, result);
        verify(projectRepository, times(1)).findWithFilters(query);
    }

    @Test
    void testFindAllProjects_WithStatusFilter() {
        FindProjectsQueryDTO query = new FindProjectsQueryDTO(
                Optional.of(ProjectStatus.ACTIVE),
                Optional.empty(),
                Optional.empty()
        );

        Project p1 = mock(Project.class);
        List<Project> mockList = List.of(p1);

        when(projectRepository.findWithFilters(query)).thenReturn(mockList);

        List<Project> result = findAllProjectsUseCase.findAllProjects(query);

        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findWithFilters(query);
    }

    @Test
    void testFindAllProjects_ReturnsEmptyList() {
        FindProjectsQueryDTO query = FindProjectsQueryDTO.empty();
        List<Project> emptyList = List.of();

        when(projectRepository.findWithFilters(query)).thenReturn(emptyList);

        List<Project> result = findAllProjectsUseCase.findAllProjects(query);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(projectRepository, times(1)).findWithFilters(query);
    }
}