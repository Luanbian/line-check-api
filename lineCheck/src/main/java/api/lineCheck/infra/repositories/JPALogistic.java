package api.lineCheck.infra.repositories;

import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import api.lineCheck.infra.interfaces.JPAs.LogisticJPArepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPALogistic implements IRepository<Logistic> {
    private final LogisticJPArepositories db;
    @Autowired
    public JPALogistic (LogisticJPArepositories db) {
        this.db = db;
    }
    @Override
    public void create(Logistic data) {
        db.save(data);
    }
}
