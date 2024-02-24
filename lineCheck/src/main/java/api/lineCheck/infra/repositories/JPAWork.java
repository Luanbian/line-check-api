package api.lineCheck.infra.repositories;

import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.domain.work.Work;
import api.lineCheck.infra.interfaces.IWorkRepository;
import api.lineCheck.infra.interfaces.WorkJPArepositories;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.NotFoundWorkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class JPAWork implements IWorkRepository {
    private final WorkJPArepositories repository;
    @Autowired
    public JPAWork(WorkJPArepositories repository) {
        this.repository = repository;
    }
    @Override
    public List<Object[]> list() {
        return repository.findDriverWorkData();
    }
    @Override
    public List<Object[]> listManager() {
        return repository.findManagerWorkData();
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
        repository.save(work);
    }
    private Work findWorkById(String workId) {
        UUID uuidWorkId = UUID.fromString(workId);
        Optional<Work> optionalWork = repository.findById(uuidWorkId);
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
}
