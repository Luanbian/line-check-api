package api.lineCheck.infra;

import api.lineCheck.core.dtos.KmDto;
import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.domain.work.Work;
import api.lineCheck.infra.interfaces.JPAs.WorkJPArepositories;
import api.lineCheck.infra.repositories.JPAWork;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.mocks.*;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.LineConflictException;
import api.lineCheck.presentation.exceptions.NotFoundWorkException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class JPAWorkTest {
    @InjectMocks
    public JPAWork sut;
    @Mock
    public WorkJPArepositories db;
    public List<Object[]> dbDriverMock = WorkDriverDbMock.main();
    public List<Object[]> dbManagerMock = WorkManagerDbMock.main();
    public List<Object[]> dbEntityNamesMock = EntityNamesDbMock.main();
    public PutRequestDriverMock requestDriverMock = new PutRequestDriverMock();
    public KmDtoMock kmDtoMock = new KmDtoMock();
    @Test
    public void should_return_list_of_object_driver_data() {
        when(db.findDriverWorkData()).thenReturn(dbDriverMock);
        List<Object[]> response = sut.list();
        assertEquals(response, dbDriverMock);
    }
    @Test
    public void should_return_list_of_object_manager_data() {
        when(db.findManagerWorkData()).thenReturn(dbManagerMock);
        List<Object[]> response = sut.listManager();
        assertEquals(response, dbManagerMock);
    }
    @Test
    public void should_return_list_of_object_entity_names() {
        when(db.findEntityNames()).thenReturn(dbEntityNamesMock);
        List<Object[]> response = sut.listEntityNames();
        assertEquals(response, dbEntityNamesMock);
    }
    @Test
    public void should_update_driver_start_journey_real() {
        String workId = requestDriverMock.workId;
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        LineChecks lineCheck = LineChecks.STARTJOURNEYREAL;

        Work work = mock(Work.class);
        when(work.getAccountId()).thenReturn(accountId);
        when(db.findById(any())).thenReturn(Optional.of(work));

        sut.updateDriverLineChecks(workId, accountId.toString(), lineCheck);
        verify(work, times(1)).setStartJourneyReal(any());
        verify(db).save(work);
    }
    @Test
    public void should_update_driver_start_line_real() {
        String workId = requestDriverMock.workId;
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        LineChecks lineCheck = LineChecks.STARTLINEREAL;

        Work work = mock(Work.class);
        when(work.getAccountId()).thenReturn(accountId);
        when(db.findById(any())).thenReturn(Optional.of(work));

        sut.updateDriverLineChecks(workId, accountId.toString(), lineCheck);
        verify(work, times(1)).setStartLineReal(any());
        verify(db).save(work);
    }
    @Test
    public void should_update_driver_end_line_real() {
        String workId = requestDriverMock.workId;
        Timestamp endTime = requestDriverMock.endLineReal;
        Timestamp startTime = requestDriverMock.startJourneyReal;
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        LineChecks lineCheck = LineChecks.ENDLINEREAL;

        Work work = mock(Work.class);
        when(work.getAccountId()).thenReturn(accountId);
        when(work.getEndLineReal()).thenReturn(endTime);
        when(work.getStartJourneyReal()).thenReturn(startTime);
        when(db.findById(any())).thenReturn(Optional.of(work));

        sut.updateDriverLineChecks(workId, accountId.toString(), lineCheck);
        verify(work, times(1)).setEndLineReal(any());
        verify(db).save(work);
    }
    @Test
    public void should_save_new_work_in_db_if_success() {
        List<Work> workList = new ArrayList<>();
        Work work = mock(Work.class);
        when(db.findWorkConflict(any(), any())).thenReturn(workList);
        sut.create(work);
        verify(db, times(1)).save(work);
    }
    @Test
    public void should_update_an_existing_work_in_db_if_success() {
        List<Work> workList = new ArrayList<>();
        Work work = mock(Work.class);
        when(db.findWorkConflict(any(),any())).thenReturn(workList);
        UUID fakeId = UUID.randomUUID();
        when(db.findById(fakeId)).thenReturn(Optional.of(work));
        sut.update(fakeId.toString(), work);
        verify(work, times(1)).setAccountId(any());
        verify(db, times(1)).save(work);
    }
    @Test
    public void should_save_km_in_db() {
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        UUID workId = UUID.fromString(requestDriverMock.workId);
        Work work = mock(Work.class);
        when(work.getAccountId()).thenReturn(accountId);
        when(db.findById(workId)).thenReturn(Optional.of(work));
        KmDto dto = kmDtoMock.main();
        sut.insertKm(workId.toString(), accountId.toString(), dto.initialKm(), dto.finalKm());
        verify(db, times(1)).save(work);
    }
    @Test
    public void should_throw_NotFoundWorkException_if_optional_work_return_empty() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        LineChecks lineCheck = LineChecks.STARTJOURNEYREAL;
        when(db.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundWorkException.class, () -> sut.updateDriverLineChecks(workId, accountId, lineCheck));
    }
    @Test
    public void should_throw_ActionNotPermittedException_if_workAccount_be_different_of_loggedAccount() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        LineChecks lineCheck = LineChecks.STARTJOURNEYREAL;

        Work work = mock(Work.class);
        when(work.getAccountId()).thenReturn(UUID.randomUUID());
        when(db.findById(any())).thenReturn(Optional.of(work));

        assertThrows(ActionNotPermittedException.class, () -> sut.updateDriverLineChecks(workId, accountId, lineCheck));
    }
    @Test
    public void should_throw_LineConflictException_if_dayOfWeek_list_match_with_list_from_db() {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        dayOfWeeks.add(DayOfWeek.FRIDAY);
        List<Work> workList = new ArrayList<>();
        Work work = mock(Work.class);
        when(work.getDaysOfTheWeek()).thenReturn(dayOfWeeks);
        workList.add(work);
        when(db.findWorkConflict(any(), any())).thenReturn(workList);
        assertThrows(LineConflictException.class, () -> sut.create(work));
    }
}
