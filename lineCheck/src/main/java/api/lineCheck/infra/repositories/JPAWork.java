package api.lineCheck.infra.repositories;

import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.domain.work.Work;
import api.lineCheck.infra.interfaces.persistances.IWorkRepository;
import api.lineCheck.infra.interfaces.JPAs.WorkJPArepositories;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.LineConflictException;
import api.lineCheck.presentation.exceptions.NotFoundWorkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.*;

@Repository
public class JPAWork implements IWorkRepository {
    private final WorkJPArepositories db;
    @Autowired
    public JPAWork(WorkJPArepositories db) {
        this.db = db;
    }
    @Override
    public List<Object[]> list() {
        return db.findDriverWorkData();
    }
    @Override
    public List<Object[]> listManager() {
        return db.findManagerWorkData();
    }
    @Override
    public List<Object[]> listEntityNames() {
        return db.findEntityNames();
    }

    @Override
    public void updateDriverLineChecks(String workId, String accountId, LineChecks lineCheck) {
        Work work = this.findWorkById(workId);
        this.compareWorkAccountAndLoggedAccount(work, accountId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        switch (lineCheck) {
            case STARTJOURNEYREAL -> work.setStartJourneyReal(now);
            case STARTLINEREAL -> work.setStartLineReal(now);
            case ENDLINEREAL -> {
                work.setEndLineReal(now);
                Time diff = calculateTimeWorkedReal(work.getEndLineReal(), work.getStartJourneyReal());
                work.setTimeWorkedReal(diff);
            }
        }
        db.save(work);
    }
    @Override
    public void insertKm(String workId, String accountId, Integer initialKm, Integer finalKm) {
        Work work = this.findWorkById(workId);
        this.compareWorkAccountAndLoggedAccount(work, accountId);
        work.setInitKm(initialKm);
        work.setFinalKm(finalKm);
        work.setTotalKm(finalKm - initialKm);
        db.save(work);
    }
    @Override
    public void create(Work data) {
        List<Work> list = db.findWorkConflict(data.getAccountId(), data.getStartLineModel());
        findWorkConflicts(list, data.getDaysOfTheWeek());
        db.save(data);
    }
    @Override
    public void update(String workId, Work data) {
        List<Work> list = db.findWorkConflict(data.getAccountId(), data.getStartLineModel());
        findWorkConflicts(list, data.getDaysOfTheWeek());
        Work work = findWorkById(workId);
        work.setAccountId(data.getAccountId());
        work.setServiceId(data.getServiceId());
        work.setDaysOfTheWeek(data.getDaysOfTheWeek());
        work.setStartJourneyModel(data.getStartJourneyModel());
        work.setStartLineModel(data.getStartLineModel());
        work.setEndLineModel(data.getEndLineModel());
        work.setTimeWorkedModel(data.getTimeWorkedModel());
        work.setLogisticId(data.getLogisticId());
        work.setVehicleId(data.getVehicleId());
        work.setManufactureId(data.getManufactureId());
        db.save(work);
    }
    private Work findWorkById(String workId) {
        UUID uuidWorkId = UUID.fromString(workId);
        Optional<Work> optionalWork = db.findById(uuidWorkId);
        if (optionalWork.isEmpty()) {
            throw new NotFoundWorkException();
        }
        return optionalWork.get();
    }
    private void compareWorkAccountAndLoggedAccount(Work workAccount, String loggedAccount) {
        String accountFromWork = workAccount.getAccountId().toString();
        if(!Objects.equals(accountFromWork, loggedAccount)) {
            throw new ActionNotPermittedException();
        }
    }
    private Time calculateTimeWorkedReal(Timestamp end, Timestamp start) {
        long diff = end.getTime() - start.getTime();
        return new Time(diff);
    }
    private void findWorkConflicts(List<Work> list, List<DayOfWeek> data) {
        List<Work> conflicts = list.stream()
                .filter(work -> work.getDaysOfTheWeek().stream()
                .anyMatch(data::contains))
                .toList();
        if(!conflicts.isEmpty()) {
            throw new LineConflictException();
        }
    }
}
