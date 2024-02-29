package api.lineCheck.data;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.data.usecase.WorkService;
import api.lineCheck.domain.work.Work;
import api.lineCheck.data.utils.entities.WorkDriver;
import api.lineCheck.data.utils.entities.WorkManager;
import api.lineCheck.domain.work.WorkProps;
import api.lineCheck.infra.repositories.JPAWork;
import api.lineCheck.mocks.*;

import static org.junit.jupiter.api.Assertions.*;

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
    public PutRequestDriverMock requestDriverMock = new PutRequestDriverMock();
    public WorkDto dtoMock = new WorkDtoMock().main();
    public WorkProps propsMock = new WorkPropsMock().main();
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
    @Test
    public void should_update_driver_linecheck_if_success() {
        String workId = requestDriverMock.workId;
        String accountId = requestDriverMock.accountId;
        String marker = requestDriverMock.marker;
        LineChecks line = LineChecks.valueOf(marker);
        sut.updateDriverLineChecks(workId, accountId, marker);
        verify(repository,times(1)).updateDriverLineChecks(workId, accountId, line);
    }
    @Test
    public void should_create_new_work() {
        Work work = Work.create(propsMock);
        sut.create(dtoMock);
        verify(repository, times(1)).create(work);
    }
    @Test
    public void should_throw_IllegalStateException_if_day_of_the_week_provide_is_invalid() {
        WorkDto dto = dtoMock;
        dto.daysOfTheWeeks().add("Invalid");
        assertThrows(IllegalStateException.class, () -> sut.create(dto));
    }
}
