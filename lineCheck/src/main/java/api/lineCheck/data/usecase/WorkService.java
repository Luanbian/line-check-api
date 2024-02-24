package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.week.DaysOfTheWeek;
import api.lineCheck.domain.work.Work;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import api.lineCheck.infra.interfaces.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.*;
import java.util.*;

@Service
public class WorkService implements IWorkService {
    private final IWorkRepository repository;
    @Autowired
    public WorkService(IWorkRepository repository) {
        this.repository = repository;
    }
    @Override
    @Transactional
    public Work create(WorkDto dto) {
        // TODO implement create new work
       return null;
    }
    @Override
    public List<WorkDriver> listWorks() {
        List<Object[]> dbResponse = repository.list();
        List<WorkDriver> works = dbResponse.stream().map(this::mapToWorkDriver).toList();
        DayOfWeek dayOfWeekNow = LocalDate.now().getDayOfWeek();
        DaysOfTheWeek today = convertToDaysOfTheWeek(dayOfWeekNow);
        return works.stream().filter(item -> item.getDaysOfTheWeek().contains(today)).toList();
    }
    @Override
    public List<WorkManager> listManagerWorks() {
        List<Object[]> dbResponse = repository.listManager();
        return dbResponse.stream().map(this::mapToWorkManager).toList();
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
    private DaysOfTheWeek convertToDaysOfTheWeek(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case SUNDAY -> DaysOfTheWeek.SUNDAY;
            case MONDAY -> DaysOfTheWeek.MONDAY;
            case TUESDAY -> DaysOfTheWeek.TUESDAY;
            case WEDNESDAY -> DaysOfTheWeek.WEDNESDAY;
            case THURSDAY -> DaysOfTheWeek.THURSDAY;
            case FRIDAY -> DaysOfTheWeek.FRIDAY;
            case SATURDAY -> DaysOfTheWeek.SATURDAY;
        };
    }
}
