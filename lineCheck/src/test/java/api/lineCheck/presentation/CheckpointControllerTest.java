package api.lineCheck.presentation;

import api.lineCheck.data.usecase.WorkService;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.mocks.WorkDriverMock;
import api.lineCheck.presentation.controllers.CheckpointController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CheckpointControllerTest {
    @InjectMocks
    public CheckpointController sut;
    @Mock
    public WorkService service;
    public WorkDriverMock workDriverMock = new WorkDriverMock();
    @Test
    public void should_return_work_list () {
        List<WorkDriver> workListMock = new ArrayList<>();
        workListMock.add(workDriverMock.main());
        when(service.listWorks()).thenReturn(workListMock);
        ResponseEntity response = sut.driverInfo();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), workListMock);
    }
}
