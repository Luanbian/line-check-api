package api.lineCheck.data.usecase;

import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.week.DaysOfTheWeek;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import api.lineCheck.infra.interfaces.IWorkRepository;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkService implements IWorkService {
    private final IWorkRepository repository;
    @Autowired
    public WorkService(IWorkRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<WorkDriver> listWorks() {
        List<Object[]> dbResponse = repository.list();
        return dbResponse.stream().map(this::mapToWorkDriver).collect(Collectors.toList());
    }
    @Override
    public List<WorkManager> listManagerWorks() {
        List<Object[]> dbResponse = repository.listManager();
        return dbResponse.stream().map(this::mapToWorkManager).collect(Collectors.toList());
    }
    @Override
    public void updateDriverLineChecks(String workId, String accountId, String marker) {
        LineChecks lineCheck = LineChecks.valueOf(marker);
        repository.updateDriverLineChecks(workId, accountId, lineCheck);
    }
    private WorkDriver mapToWorkDriver(Object[] item) {
        return new WorkDriver(
                (UUID) item[0],
                (String) item[1],
                (Time) item[2],
                (Time) item[3],
                (Time) item[4],
                (String) item[5],
                (String) item[6],
                (String) item[7],
                (String) item[8],
                (List<DaysOfTheWeek>) item[9]
        );
    }
    private WorkManager mapToWorkManager(Object[] item) {
        return new WorkManager(
                (UUID) item[0],
                (String) item[1],
                (Time) item[2],
                (Timestamp) item[3],
                (Time) item[4],
                (Timestamp) item[5],
                (Time) item[6],
                (Timestamp) item[7],
                (String) item[8],
                (String) item[9],
                (String) item[10],
                (String) item[11],
                (List<DaysOfTheWeek>) item[12]
        );
    }
}
