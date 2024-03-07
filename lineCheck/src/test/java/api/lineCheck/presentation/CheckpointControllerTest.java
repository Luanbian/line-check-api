package api.lineCheck.presentation;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.usecase.WorkService;
import api.lineCheck.data.utils.entities.EntityNames;
import api.lineCheck.data.utils.entities.WorkDriver;
import api.lineCheck.data.utils.entities.WorkManager;
import api.lineCheck.mocks.*;
import api.lineCheck.presentation.controllers.CheckpointController;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.LineConflictException;
import api.lineCheck.presentation.helpers.ResponseBody;
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
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CheckpointControllerTest {
    @InjectMocks
    public CheckpointController sut;
    @Mock
    public WorkService service;
    public WorkDriverMock workDriverMock = new WorkDriverMock();
    public WorkManagerMock workManagerMock = new WorkManagerMock();
    public PutRequestDriverMock requestDriverMock = new PutRequestDriverMock();
    public EntityNamesMock entityNamesMock = new EntityNamesMock();
    public WorkDtoMock workDtoMock = new WorkDtoMock();
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
        List<EntityNames> entityNamesListMock = new ArrayList<>();
        entityNamesListMock.add(entityNamesMock.main());
        when(service.listEntityNames()).thenReturn(entityNamesListMock);
        ResponseBody responseBodyMock = ResponseBody.create(workManagerListMock, entityNamesListMock);
        ResponseEntity response = sut.managerInfo();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), responseBodyMock);
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
    @Test
    public void should_return_ok_if_update_linecheck() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        String marker = requestDriverMock.marker;
        ResponseEntity response = sut.driverUpdateLineCheck(workId, accountId, marker);
        verify(service, times(1)).updateDriverLineChecks(workId, accountId, marker);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void should_fall_in_catch_if_user_try_update_other_linecheck() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        String marker = requestDriverMock.marker;
        doAnswer(invocation -> {
            throw new ActionNotPermittedException();
        }).when(service).updateDriverLineChecks(workId, accountId, marker);
        ResponseEntity response = sut.driverUpdateLineCheck(workId, accountId, marker);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), "Ação não permitida, você não pode alterar dados de outro funcionário");
    }
    @Test
    public void should_fall_in_catch_if_service_throw() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        String marker = requestDriverMock.marker;
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).updateDriverLineChecks(workId, accountId, marker);
        ResponseEntity response = sut.driverUpdateLineCheck(workId, accountId, marker);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(), "Erro interno do servidor");
    }
    @Test
    public void should_return_ok_if_create_new_line_with_success() {
        WorkDto dto = workDtoMock.main();
        ResponseEntity response = sut.createLine(dto);
        verify(service, times(1)).create(dto);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void should_return_ok_if_update_existing_line_with_success() {
        WorkDto dto = workDtoMock.main();
        String fakeWorkId = UUID.randomUUID().toString();
        ResponseEntity response = sut.updateLine(fakeWorkId, dto);
        verify(service, times(1)).update(fakeWorkId, dto);
    }
    @Test
    public void should_throw_LineConflictException_if_exists_conflict_to_create_line() {
        WorkDto dto = workDtoMock.main();
        doAnswer(invocation -> {
            throw new LineConflictException();
        }).when(service).create(dto);
        ResponseEntity response = sut.createLine(dto);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), "Existe um conflito de horários e datas");
    }
    @Test
    public void should_fall_in_catch_if_service_throws() {
        WorkDto dto = workDtoMock.main();
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).create(dto);
        ResponseEntity response = sut.createLine(dto);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(), "Erro interno do servidor");
    }
}
