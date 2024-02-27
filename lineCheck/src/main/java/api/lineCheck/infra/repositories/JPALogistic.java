package api.lineCheck.infra.repositories;

import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import api.lineCheck.infra.interfaces.JPAs.LogisticJPArepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPALogistic implements IRepository<Logistic> {
    private final LogisticJPArepositories repository;
    @Autowired
    public JPALogistic (LogisticJPArepositories repository) {
        this.repository = repository;
    }
    @Override
    public void create(Logistic data) {
        repository.save(data);
    }
}
