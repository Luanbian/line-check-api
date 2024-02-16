package api.lineCheck.data;

import api.lineCheck.data.usecase.WorkService;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import api.lineCheck.infra.repositories.JPAWork;
import api.lineCheck.mocks.WorkDriverDbMock;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.mocks.WorkManagerDbMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WorkServiceTest {
    @InjectMocks
    public WorkService sut;
    @Mock
    public JPAWork repository;
    public List<Object[]> dbDriverMock = WorkDriverDbMock.main();
    public List<Object[]> dbManagerMock = WorkManagerDbMock.main();
    @Test
    public void should_return_list_of_WorkDriver() {
        when(repository.list()).thenReturn(dbDriverMock);
        List<WorkDriver> response = sut.listWorks();
        verify(repository, times(1)).list();
        assertEquals(response.get(0).getId(), dbDriverMock.get(0)[0]);
        assertEquals(response.get(0).getAccountName(), dbDriverMock.get(0)[1]);
    }
    @Test
    public void should_return_list_of_WorkManager() {
        when(repository.listManager()).thenReturn(dbManagerMock);
        List<WorkManager> response = sut.listManagerWorks();
        verify(repository, times(1)).listManager();
        assertEquals(response.get(0).getId(), dbManagerMock.get(0)[0]);
        assertEquals(response.get(0).getStartJourneyReal(), dbManagerMock.get(0)[3]);
    }
}
