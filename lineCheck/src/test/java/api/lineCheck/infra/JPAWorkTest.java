package api.lineCheck.infra;

import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.repositories.JPAWork;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.mocks.WorkDriverDbMock;
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
    public List<Object[]> dbMock = WorkDriverDbMock.main();
    @Test
    public void should_return_list_of_object_driver_data () {
        when(repository.findDriverWorkData()).thenReturn(dbMock);
        List<Object[]> response = sut.list();
        assertEquals(response, dbMock);
    }
}
