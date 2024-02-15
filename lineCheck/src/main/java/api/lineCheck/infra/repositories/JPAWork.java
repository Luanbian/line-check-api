package api.lineCheck.infra.repositories;

import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.interfaces.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JPAWork implements IWorkRepository {
    private final AccountJPArepositories repository;
    @Autowired
    public JPAWork(AccountJPArepositories repository) {
        this.repository = repository;
    }
    @Override
    public List<WorkDriver> list() {
        return repository.findDriverWorkData();
    }
}
