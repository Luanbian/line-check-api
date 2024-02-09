package api.lineCheck.infra.repositories;

import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.interfaces.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JPAWork implements IWorkRepository {
    private final AccountJPArepositories repository;
    @Autowired
    public JPAWork(AccountJPArepositories repository) {
        this.repository = repository;
    }
    @Override
    public List<Object[]> list() {
        return repository.findDriverWorkData();
    }
}
