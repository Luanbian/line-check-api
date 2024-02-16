package api.lineCheck.data;

import api.lineCheck.data.usecase.WorkService;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.infra.repositories.JPAWork;
import api.lineCheck.mocks.WorkDriverDbMock;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WorkServiceTest {
    @InjectMocks
    public WorkService sut;
    @Mock
    public JPAWork repository;
    public List<Object[]> dbMock = WorkDriverDbMock.main();
    @Test
    public void should_call_repository () {
        sut.listWorks();
        verify(repository, times(1)).list();
    }
    @Test
    public void should_return_list_of_WorkDriver () {
        when(repository.list()).thenReturn(dbMock);
        List<WorkDriver> response = sut.listWorks();
        assertEquals(response.get(0).getId(), dbMock.get(0)[0]);
        assertEquals(response.get(0).getAccountName(), dbMock.get(0)[1]);
    }
}
