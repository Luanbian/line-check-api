package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.data.utils.entities.EntityNames;
import api.lineCheck.data.utils.mappers.ConvertStringToDayOfWeek;
import api.lineCheck.data.utils.mappers.MapToEntityNames;
import api.lineCheck.data.utils.mappers.MapToWorkDriver;
import api.lineCheck.data.utils.mappers.MapToWorkManager;
import api.lineCheck.domain.work.Work;
import api.lineCheck.data.utils.entities.WorkDriver;
import api.lineCheck.data.utils.entities.WorkManager;
import api.lineCheck.domain.work.WorkProps;
import api.lineCheck.infra.interfaces.persistances.IWorkRepository;
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
        WorkProps props = convertDtoToProps(dto);
        Work work = Work.create(props);
        repository.create(work);
        return work;
    }
    @Override
    public List<WorkDriver> listWorks() {
        List<Object[]> dbResponse = repository.list();
        List<WorkDriver> works = dbResponse.stream().map(MapToWorkDriver::main).toList();
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return works.stream().filter(item -> item.getDaysOfTheWeek().contains(today)).toList();
    }
    @Override
    public List<WorkManager> listManagerWorks() {
        List<Object[]> dbResponseWorks = repository.listManager();
        return dbResponseWorks.stream().map(MapToWorkManager::main).toList();
    }
    @Override
    public List<EntityNames> listEntityNames() {
        List<Object[]> dbResponseEntities = repository.listEntityNames();
        return dbResponseEntities.stream().map(MapToEntityNames::main).toList();
    }
    @Override
    public void updateDriverLineChecks(String workId, String accountId, String marker) {
        LineChecks lineCheck = LineChecks.valueOf(marker);
        repository.updateDriverLineChecks(workId, accountId, lineCheck);
    }
    private WorkProps convertDtoToProps (WorkDto dto) {
        UUID accountUUID = UUID.fromString(dto.accountId());
        UUID driverServiceUUID = UUID.fromString(dto.serviceId());
        UUID logisticUUID = UUID.fromString(dto.logisticId());
        UUID vehicleUUID = UUID.fromString(dto.vehicleId());
        UUID manufactureUUID = UUID.fromString(dto.manufactureId());
        List<DayOfWeek> days = dto.daysOfTheWeeks().stream().map(ConvertStringToDayOfWeek::main).toList();
        LocalTime startJourneyLocal = LocalTime.parse(dto.startJourneyModel());
        LocalTime startLineLocal = LocalTime.parse(dto.startLineModel());
        LocalTime endLineLocal = LocalTime.parse(dto.endLineModel());
        Time startJorneyTime = Time.valueOf(startJourneyLocal);
        Time startLineTime = Time.valueOf(startLineLocal);
        Time endLineTime = Time.valueOf(endLineLocal);
        return new WorkProps(
                accountUUID, driverServiceUUID, days, startJorneyTime, startLineTime,
                endLineTime, logisticUUID,vehicleUUID, manufactureUUID
        );
    }
}
