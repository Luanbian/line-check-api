package api.lineCheck.infra;

import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.repositories.JPAWork;
import api.lineCheck.mocks.WorkDriverMock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JPAWorkTest {
    @InjectMocks
    public JPAWork sut;
    @Mock
    public AccountJPArepositories repository;
    public WorkDriverMock workDriverMock = new WorkDriverMock();
    @Test
    public void should_return_list_of_WorkDriver_if_success () {
        List<WorkDriver> dbResponseMock = new ArrayList<>();
        dbResponseMock.add(workDriverMock.main());
        System.out.println(dbResponseMock);
        when(repository.findDriverWorkData()).thenReturn(dbResponseMock);
        List<WorkDriver> response = sut.list();
        assertEquals(response, dbResponseMock);
    }
}
