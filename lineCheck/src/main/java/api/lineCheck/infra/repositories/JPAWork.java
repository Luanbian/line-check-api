package api.lineCheck.infra.repositories;

import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.interfaces.IWorkRepository;
import api.lineCheck.infra.interfaces.WorkJPArepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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
}
