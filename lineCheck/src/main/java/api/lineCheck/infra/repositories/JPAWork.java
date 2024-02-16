package api.lineCheck.infra.repositories;

import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.domain.work.Work;
import api.lineCheck.infra.interfaces.IWorkRepository;
import api.lineCheck.infra.interfaces.WorkJPArepositories;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        UUID uuidWorkId = UUID.fromString(workId);
        Optional<Work> optionalWork = repository.findById(uuidWorkId);
        if(optionalWork.isPresent()) {
            Work work = optionalWork.get();
            String accountFromWork = work.getAccount().getId().toString();
            if (Objects.equals(accountFromWork, accountId)) {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                switch (lineCheck) {
                    case STARTJOURNEYREAL -> work.setStartJourneyReal(now);
                    case STARTLINEREAL -> work.setStartLineReal(now);
                    case ENDLINEREAL -> work.setEndLineReal(now);
                }
                repository.save(work);
            } else {
                throw new ActionNotPermittedException();
            }
        }
    }
}
