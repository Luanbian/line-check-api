package api.lineCheck.infra;

import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.work.Work;
import api.lineCheck.infra.interfaces.WorkJPArepositories;
import api.lineCheck.infra.repositories.JPAWork;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.mocks.PutRequestDriverMock;
import api.lineCheck.mocks.WorkDriverDbMock;
import api.lineCheck.mocks.WorkManagerDbMock;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.NotFoundWorkException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class JPAWorkTest {
    @InjectMocks
    public JPAWork sut;
    @Mock
    public WorkJPArepositories repository;
    public List<Object[]> dbDriverMock = WorkDriverDbMock.main();
    public List<Object[]> dbManagerMock = WorkManagerDbMock.main();
    public PutRequestDriverMock requestDriverMock = new PutRequestDriverMock();
    @Test
    public void should_return_list_of_object_driver_data() {
        when(repository.findDriverWorkData()).thenReturn(dbDriverMock);
        List<Object[]> response = sut.list();
        assertEquals(response, dbDriverMock);
    }
    @Test
    public void should_return_list_of_object_manager_data() {
        when(repository.findManagerWorkData()).thenReturn(dbManagerMock);
        List<Object[]> response = sut.listManager();
        assertEquals(response, dbManagerMock);
    }
    @Test
    public void should_update_driver_start_journey_real() {
        String workId = requestDriverMock.workId;
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        LineChecks lineCheck = LineChecks.STARTJOURNEYREAL;

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(accountId);

        Work work = mock(Work.class);
        when(work.getAccount()).thenReturn(account);
        when(repository.findById(any())).thenReturn(Optional.of(work));

        sut.updateDriverLineChecks(workId, accountId.toString(), lineCheck);
        verify(work, times(1)).setStartJourneyReal(any());
        verify(repository).save(work);
    }
    @Test
    public void should_update_driver_start_line_real() {
        String workId = requestDriverMock.workId;
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        LineChecks lineCheck = LineChecks.STARTLINEREAL;

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(accountId);

        Work work = mock(Work.class);
        when(work.getAccount()).thenReturn(account);
        when(repository.findById(any())).thenReturn(Optional.of(work));

        sut.updateDriverLineChecks(workId, accountId.toString(), lineCheck);
        verify(work, times(1)).setStartLineReal(any());
        verify(repository).save(work);
    }
    @Test
    public void should_update_driver_end_line_real() {
        String workId = requestDriverMock.workId;
        UUID accountId = UUID.fromString(requestDriverMock.accountId);
        LineChecks lineCheck = LineChecks.ENDLINEREAL;

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(accountId);

        Work work = mock(Work.class);
        when(work.getAccount()).thenReturn(account);
        when(repository.findById(any())).thenReturn(Optional.of(work));

        sut.updateDriverLineChecks(workId, accountId.toString(), lineCheck);
        verify(work, times(1)).setEndLineReal(any());
        verify(repository).save(work);
    }
    @Test
    public void should_throw_NotFoundWorkException_if_optional_work_return_empty() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        LineChecks lineCheck = LineChecks.STARTJOURNEYREAL;
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundWorkException.class, () -> sut.updateDriverLineChecks(workId, accountId, lineCheck));
    }
    @Test
    public void should_throw_ActionNotPermittedException_if_workAccount_be_different_of_loggedAccount() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        LineChecks lineCheck = LineChecks.STARTJOURNEYREAL;

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(UUID.randomUUID());

        Work work = mock(Work.class);
        when(work.getAccount()).thenReturn(account);
        when(repository.findById(any())).thenReturn(Optional.of(work));

        assertThrows(ActionNotPermittedException.class, () -> sut.updateDriverLineChecks(workId, accountId, lineCheck));
    }
}
