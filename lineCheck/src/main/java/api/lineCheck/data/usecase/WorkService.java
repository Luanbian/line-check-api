package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.work.Work;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import api.lineCheck.domain.work.WorkProps;
import api.lineCheck.infra.interfaces.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

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
        UUID accountUUID = UUID.fromString(dto.accountId());
        UUID driverServiceUUID = UUID.fromString(dto.serviceId());
        List<DayOfWeek> days = dto.daysOfTheWeeks().stream().map(this::mapToDayOfWeek).toList();
        LocalTime startJourneyLocal = LocalTime.parse(dto.startJourneyModel());
        LocalTime startLineLocal = LocalTime.parse(dto.startLineModel());
        LocalTime endLineLocal = LocalTime.parse(dto.endLineModel());
        Time startJorneyTime = Time.valueOf(startJourneyLocal);
        Time startLineTime = Time.valueOf(startLineLocal);
        Time endLineTime = Time.valueOf(endLineLocal);
        UUID logisticUUID = UUID.fromString(dto.logisticId());
        UUID vehicleUUID = UUID.fromString(dto.vehicleId());
        UUID manufactureUUID = UUID.fromString(dto.manufactureId());
        WorkProps props = new WorkProps(accountUUID, driverServiceUUID, days, startJorneyTime, startLineTime, endLineTime, logisticUUID,vehicleUUID, manufactureUUID);
        return Work.create(props);
    }
    @Override
    public List<WorkDriver> listWorks() {
        List<Object[]> dbResponse = repository.list();
        List<WorkDriver> works = dbResponse.stream().map(this::mapToWorkDriver).toList();
        DayOfWeek today = LocalDate.now().getDayOfWeek();
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
                (List<DayOfWeek>) item[9]
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
                (List<DayOfWeek>) item[12]
        );
    }
    private DayOfWeek mapToDayOfWeek(String item) {
        return switch (item) {
            case "SUNDAY" -> DayOfWeek.SUNDAY;
            case "MONDAY" -> DayOfWeek.MONDAY;
            case "TUESDAY" -> DayOfWeek.TUESDAY;
            case "WEDNESDAY" -> DayOfWeek.WEDNESDAY;
            case "THURSDAY" -> DayOfWeek.THURSDAY;
            case "FRIDAY" -> DayOfWeek.FRIDAY;
            case "SATURDAY" -> DayOfWeek.SATURDAY;
            default -> throw new IllegalStateException("Unexpected day of the week: " + item);
        };
    }
}
