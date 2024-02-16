package api.lineCheck.presentation;

import api.lineCheck.data.usecase.WorkService;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import api.lineCheck.mocks.WorkDriverMock;
import api.lineCheck.mocks.WorkManagerMock;
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
    public WorkManagerMock workManagerMock = new WorkManagerMock();
    @Test
    public void should_return_work_list () {
        List<WorkDriver> workListMock = new ArrayList<>();
        workListMock.add(workDriverMock.main());
        when(service.listWorks()).thenReturn(workListMock);
        ResponseEntity response = sut.driverInfo();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), workListMock);
    }
    @Test
    public void should_return_no_content_if_list_works_is_empty () {
        List<WorkDriver> workListMock = new ArrayList<>();
        when(service.listWorks()).thenReturn(workListMock);
        ResponseEntity response = sut.driverInfo();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
    @Test
    public void should_fall_in_catch_if_driver_service_throw () {
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).listWorks();
        ResponseEntity response = sut.driverInfo();
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(), "Erro interno do servidor");
    }
    @Test
    public void should_return_work_manager_list() {
        List<WorkManager> workManagerListMock = new ArrayList<>();
        workManagerListMock.add(workManagerMock.main());
        when(service.listManagerWorks()).thenReturn(workManagerListMock);
        ResponseEntity response = sut.managerInfo();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), workManagerListMock);
    }
    @Test
    public void should_return_no_content_if_list_manager_works_is_empty() {
        List<WorkManager> workManagerListMock = new ArrayList<>();
        when(service.listManagerWorks()).thenReturn(workManagerListMock);
        ResponseEntity response = sut.managerInfo();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
    @Test
    public void should_fall_in_catch_if_manager_service_throw () {
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).listManagerWorks();
        ResponseEntity response = sut.managerInfo();
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(), "Erro interno do servidor");
    }
}
